package me.gogradually.springaitest.application.task

import me.gogradually.springaitest.application.task.query.existtask.TaskQueryPort
import me.gogradually.springaitest.application.task.query.split.SplitTask
import me.gogradually.springaitest.application.task.query.split.TaskSpliter
import me.gogradually.springaitest.domain.task.Task
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class TaskSplitService(taskQueryPort: TaskQueryPort, taskSpliter: TaskSpliter) {
    private val taskQueryPort: TaskQueryPort = taskQueryPort
    private val taskSpliter: TaskSpliter = taskSpliter
    fun split(id: Long, title: String, description: String): Mono<SplitTask> {
        val (finishedTasks, notFinishedTasks) = taskQueryPort.getSchedules(id)
        return taskSpliter.generateTaskPlan(title, description, "apprentice", finishedTasks, notFinishedTasks)
    }
}