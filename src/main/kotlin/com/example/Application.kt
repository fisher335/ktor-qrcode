package com.example

import com.example.plugins.configureHTTP
import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import com.example.router.fileRouting
import com.example.router.qrRouting
import io.ktor.server.application.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
//    系统默认配置
    configureHTTP()
    configureSerialization()
//    自定义路由配置
    configureRouting()
    fileRouting()
    qrRouting()
}
