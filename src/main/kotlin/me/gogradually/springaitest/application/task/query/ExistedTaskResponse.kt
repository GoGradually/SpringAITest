package me.gogradually.springaitest.application.task.query

data class ExistedTaskResponse(val finishedTasks: List<TaskScript>, val notFinishedTasks: List<TaskScript>)
