package com.example.router

import com.example.plugins.MySession
import com.example.utils.FileUtils
import com.example.utils.jsonOk
import com.example.utils.param
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*

fun Application.qrRouting() {
    routing {
        get("/") {
            val params: String? = call.param("url")
            call.jsonOk("success", mapOf("url" to params).toString())
        }
        get("/qrcode") {
            val qrcodeString: String? = call.request.queryParameters["url"]
            val fileName = qrcodeString?.let { it1 -> FileUtils.createQRcode(it1) }
            call.respond(mapOf("data" to fileName))
        }
        get("/session") {
            val s = call.sessions.get("MySession") as? MySession
            if (s == null) {
                call.sessions.set("MySession", MySession("login", "fengshaomin"))
                call.respondText { "generated new session" }
            } else {
                call.sessions.clear("MySession")
                call.respondText { "name: ${s.name}, data: ${s.data}" }

            }
        }
    }
}