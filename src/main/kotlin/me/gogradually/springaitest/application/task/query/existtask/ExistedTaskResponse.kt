package me.gogradually.springaitest.application.task.query.existtask

data class ExistedTaskResponse(val finishedTasks: List<TaskScript>, val notFinishedTasks: List<TaskScript>)
