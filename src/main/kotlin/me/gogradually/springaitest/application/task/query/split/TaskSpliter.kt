package me.gogradually.springaitest.application.task.query.split

import me.gogradually.springaitest.application.task.query.existtask.TaskScript
import me.gogradually.springaitest.domain.task.Task
import reactor.core.publisher.Mono

interface TaskSpliter {
    fun generateTaskPlan(goal: String, description: String, expertise: String, finishedTask: List<TaskScript>, notFinishedTask: List<TaskScript>): Mono<SplitTask>
}