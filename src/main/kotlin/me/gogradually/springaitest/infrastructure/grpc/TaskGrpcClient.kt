package me.gogradually.springaitest.infrastructure.grpc

import me.gogradually.grpcmockserver.proto.MockGrpcServiceGrpc
import me.gogradually.grpcmockserver.proto.TaskGrpcRequest
import me.gogradually.springaitest.application.task.query.existtask.ExistedTaskResponse
import me.gogradually.springaitest.application.task.query.existtask.TaskQueryPort
import me.gogradually.springaitest.application.task.query.existtask.TaskScript


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