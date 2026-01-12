package me.gogradually.springaitest.infrastructure.grpc

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import me.gogradually.grpcmockserver.proto.MockGrpcServiceGrpc
import me.gogradually.springaitest.application.task.query.existtask.TaskQueryPort
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(TaskGrpcProperties::class)
class TaskGrpcBeanConfig {

    @Bean(destroyMethod = "shutdown")
    fun managedChannel(taskGrpcProperties: TaskGrpcProperties): ManagedChannel {
        return ManagedChannelBuilder.forAddress(taskGrpcProperties.host, taskGrpcProperties.port)
            .usePlaintext()
            .build()
    }

    @Bean
    fun mockGrpcServiceStub(channel: ManagedChannel): MockGrpcServiceGrpc.MockGrpcServiceBlockingStub {
        return MockGrpcServiceGrpc.newBlockingStub(channel)
    }

    @Bean
    fun taskQueryPort(stub: MockGrpcServiceGrpc.MockGrpcServiceBlockingStub): TaskQueryPort {
        return TaskGrpcClient(stub)
    }
}