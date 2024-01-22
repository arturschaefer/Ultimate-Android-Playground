package com.schaefer.playground.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val response: Venues,
)

//{"meta":{"code":200,"requestId":"65ad8210a5cc3078e158f4a3"},"response":{"venues":[
