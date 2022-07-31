package com.example.router

import com.example.utils.FileUtils
import com.example.utils.param
import com.example.utils.warn
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.QrRouting() {
    routing {
        get("/") {
            val params: String = call.param("url")
            call.respondText("------$params----")
        }
        get("/qrcode") {
            val qrcodeString: String? = call.request.queryParameters["url"]
            val fileName = qrcodeString?.let { it1 -> FileUtils.createQRcode(it1) }
            call.respond(mapOf("data" to fileName))
        }
    }
}