package me.gogradually.springaitest.application.task.query

import me.gogradually.springaitest.domain.task.Task

interface TaskQueryPort {
    fun getSchedules(id: Long): ExistedTaskResponse
}