package me.gogradually.springaitest.interfaces.openai

import me.gogradually.springaitest.infrastructure.openai.TaskPlan
import me.gogradually.springaitest.infrastructure.openai.TaskService
import me.gogradually.springaitest.interfaces.openai.dto.GoalRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class TaskController(private val taskService: TaskService) {

    @PostMapping("/plan")
    fun planTasks(@RequestBody request: GoalRequest): Mono<TaskPlan> {
        return taskService.generateTaskPlan(request.goal)
    }
}