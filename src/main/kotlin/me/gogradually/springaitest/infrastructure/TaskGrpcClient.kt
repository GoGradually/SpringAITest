package me.gogradually.springaitest.infrastructure

import me.gogradually.grpcmockserver.proto.MockGrpcServiceGrpc
import me.gogradually.grpcmockserver.proto.TaskGrpcRequest
import me.gogradually.springaitest.application.task.query.ExistedTaskResponse
import me.gogradually.springaitest.application.task.query.TaskQueryPort
import me.gogradually.springaitest.application.task.query.TaskScript
import me.gogradually.springaitest.domain.task.Task


class TaskGrpcClient(taskStub: MockGrpcServiceGrpc.MockGrpcServiceBlockingStub) : TaskQueryPort {
    private val stub: MockGrpcServiceGrpc.MockGrpcServiceBlockingStub = taskStub
    override fun getSchedules(id: Long): ExistedTaskResponse {
        val grpcResponse = stub.getTasks(
            TaskGrpcRequest.newBuilder()
                .setId(id)
                .build()
        )
        return ExistedTaskResponse(
            finishedTasks = grpcResponse.finishedTasksList.map {
                TaskScript(
                    title = it.title,
                    description = it.description
                )
            },
            notFinishedTasks = grpcResponse.notFinishedTasksList.map {
                TaskScript(
                    title = it.title,
                    description = it.description
                )
            }
        )
    }

}