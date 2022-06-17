package com.example.router

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.fileRouting() {
    routing {
        route("/file"){
            get { call.respondText("file is now !!") }
        }
    }
}