package com.example.utils

import io.ktor.server.application.*

fun ApplicationCall.param(param: String) =
//    parameters[param] ?: throw ValidationException(mapOf("param" to listOf("can't be empty")))
    parameters[param] ?: "null"