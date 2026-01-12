package me.gogradually.springaitest.interfaces.task

import me.gogradually.springaitest.application.task.TaskSplitService
import me.gogradually.springaitest.application.task.query.split.SplitTask
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class TaskSplitController(private val taskSplitService: TaskSplitService) {
    @PostMapping("/tasks/split")
    fun splitTasks(id: Long, title: String, description: String): Mono<SplitTask> {
        return taskSplitService.split(id, title, description)
    }
}