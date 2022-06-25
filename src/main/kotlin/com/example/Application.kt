package com.example

import io.ktor.server.application.*
import com.example.plugins.*
import com.example.router.fileRouting

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureHTTP()
    configureRouting()
    fileRouting()
    configureSerialization()
}
