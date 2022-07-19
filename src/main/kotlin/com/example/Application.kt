package com.example

import io.ktor.server.application.*
import com.example.plugins.*
import com.example.router.QrRouting
import com.example.router.fileRouting

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
//    系统默认配置
    configureHTTP()
    configureSerialization()
//    自定义路由配置
    configureRouting()
    fileRouting()
    QrRouting()
}
