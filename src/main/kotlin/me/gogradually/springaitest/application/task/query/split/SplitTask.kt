package me.gogradually.springaitest.application.task.query.split

import me.gogradually.springaitest.domain.task.Task

data class SplitTask(val title: String, val split: List<Task>)
