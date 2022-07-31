package com.example.utils

import io.ktor.server.application.*

// 读取配置文件 - string
fun ApplicationCall.getConfString(key: String, def: String = ""): String {
    return this.application.getConfString(key, def)
}

fun Application.getConfString(key: String, def: String = ""): String {
    return this.environment.config.propertyOrNull(key)?.getString() ?: def
}