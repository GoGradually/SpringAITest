package me.gogradually.springaitest.application.openai

data class TaskPlan(val goal: String, val steps: List<TaskStep>)
