package com.example.router

import com.example.utils.FileUtils
import com.example.utils.jsonOk
import com.example.utils.param
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.qrRouting() {
    routing {
        get("/") {
            val params: String = call.param("url")
//            call.respondText("------$params----")
            call.jsonOk("suce", null)
        }
        get("/qrcode") {
            val qrcodeString: String? = call.request.queryParameters["url"]
            val fileName = qrcodeString?.let { it1 -> FileUtils.createQRcode(it1) }
            call.respond(mapOf("data" to fileName))
        }
    }
}