package com.example.api

import com.example.plugins.MySession
import com.example.utils.jsonOk
import com.example.utils.param
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*


fun Route.testRouting() {

    get("/{url}") {
        val params: String? = call.param("url")
        call.jsonOk("success", mapOf("url" to params))
    }
    get("/session") {
        val s = call.sessions.get("Session") as? MySession
        if (s == null) {
            call.sessions.set("Session", MySession("login", "fengshaomin"))
            call.jsonOk("success", mapOf("msg" to "generated new session"))
        } else {
            call.sessions.clear<MySession>()
            call.jsonOk("success", mapOf("msg" to "name: ${s.name}, data: ${s.data}"))
        }

    }
}
