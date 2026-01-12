package me.gogradually.springaitest.infrastructure.taskspliter.mock

import me.gogradually.springaitest.application.task.query.existtask.TaskScript
import me.gogradually.springaitest.application.task.query.split.SplitTask
import me.gogradually.springaitest.application.task.query.split.TaskSpliter
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class MockTaskSpliter(
    @Value("\${mock.server.url}") private val mockServerUrl: String,
    private val webClientBuilder: WebClient.Builder
) : TaskSpliter {

    private val webClient: WebClient by lazy {
        webClientBuilder.baseUrl(mockServerUrl).build()
    }

    override fun generateTaskPlan(
        goal: String,
        description: String,
        expertise: String,
        finishedTask: List<TaskScript>,
        notFinishedTask: List<TaskScript>
    ): Mono<SplitTask> {
        return webClient.post()
            .uri("/tasks/split")
            .bodyValue(
                mapOf(
                    "title" to goal,
                    "content" to description,
                    "expertise" to expertise,
                    "finishedTask" to finishedTask,
                    "notFinishedTask" to notFinishedTask
                )
            )
            .retrieve()
            .bodyToMono(SplitTask::class.java)
    }

}