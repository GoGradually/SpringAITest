package me.gogradually.springaitest.interfaces.openai

import me.gogradually.springaitest.infrastructure.openai.OpenAiTaskPlan
import me.gogradually.springaitest.infrastructure.openai.OpenAiTaskService
import me.gogradually.springaitest.interfaces.openai.dto.GoalRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class TaskController(private val openAiTaskService: OpenAiTaskService) {

    @PostMapping("/plan")
    fun planTasks(@RequestBody request: GoalRequest): Mono<OpenAiTaskPlan> {
        return openAiTaskService.generateTaskPlan(request.goal)
    }
}