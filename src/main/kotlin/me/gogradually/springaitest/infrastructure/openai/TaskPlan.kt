package me.gogradually.springaitest.infrastructure.openai

data class TaskPlan(val goal: String, val steps: List<TaskStep>)
