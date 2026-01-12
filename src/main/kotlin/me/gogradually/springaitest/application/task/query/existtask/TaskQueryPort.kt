package me.gogradually.springaitest.application.task.query.existtask

interface TaskQueryPort {
    fun getSchedules(id: Long): ExistedTaskResponse
}