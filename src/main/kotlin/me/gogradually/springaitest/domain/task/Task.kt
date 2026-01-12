package me.gogradually.springaitest.domain.task

class Task(
    title: String,
    description: String,
    step: Int,
    estimatedTime: Int
) {
    var title:String = title
        private set
    var description:String = description
        private set
    var step: Int = step
        private set
    var estimatedTime: Int = estimatedTime
        private set
}