package com.example

import com.example.plugins.configureHTTP
import com.example.plugins.configureMonitoring
import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import io.ktor.server.application.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
//    系统默认配置
    configureHTTP()
    configureSerialization()
    configureMonitoring()
//    自定义路由配置
    configureRouting()

}
