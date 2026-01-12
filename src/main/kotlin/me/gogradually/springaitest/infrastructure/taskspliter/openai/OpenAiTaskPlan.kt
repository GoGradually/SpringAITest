package me.gogradually.springaitest.infrastructure.taskspliter.openai

data class OpenAiTaskPlan(val goal: String, val steps: List<OpenAiTaskStep>)
