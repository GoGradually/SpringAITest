package me.gogradually.springaitest.infrastructure

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "task.grpc")
data class TaskGrpcProperties(val host: String, val port: Int)
