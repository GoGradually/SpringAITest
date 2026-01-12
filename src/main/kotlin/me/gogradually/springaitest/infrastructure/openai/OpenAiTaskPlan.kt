package me.gogradually.springaitest.infrastructure.openai

data class OpenAiTaskPlan(val goal: String, val steps: List<OpenAiTaskStep>)
