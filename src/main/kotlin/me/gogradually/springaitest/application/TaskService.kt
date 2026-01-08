package me.gogradually.springaitest.application

import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.chat.prompt.PromptTemplate
import org.springframework.ai.converter.BeanOutputConverter
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClientResponseException
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

@Service
class TaskService (private val chatClientBuilder: ChatClient.Builder) {
    private val log = LoggerFactory.getLogger(TaskService::class.java)
    private val chatClient = chatClientBuilder.build()
    val outputConverter = BeanOutputConverter(TaskPlan::class.java) // Spring AI의 structured output 변환기

    private val template = """
            Given the user goal: "{goal}", break it into step-by-step tasks.
            Respond ONLY with a JSON object matching the TaskPlan schema, nothing else:
            {format}
        """.trimIndent()

    fun generateTaskPlan(goal: String): Mono<TaskPlan> {


        val prompt = PromptTemplate(template).create(mapOf("goal" to goal, "format" to outputConverter.format))

        return chatClient.prompt(prompt)
            .stream()
            .content()
            .collectList()
            .map { it.joinToString("") }
            .map { it.trim() }
            .filter { it.isNotBlank() }
            .switchIfEmpty {
                Mono.error(RuntimeException("No response from AI"))
            }
            .map { outputConverter.convert(it) }
            .doOnError { t ->
                val wcre = t.findWebClientResponseException()
                if (wcre != null) {
                    log.error(
                        "OpenAI HTTP error. status={}, body={}",
                        wcre.statusCode,
                        wcre.responseBodyAsString
                    )
                } else {
                    log.error("AI call failed", t)
                }
            }
    }
    fun Throwable.findWebClientResponseException(): WebClientResponseException? =
        generateSequence(this) { it.cause }
            .filterIsInstance<WebClientResponseException>()
            .firstOrNull()
}