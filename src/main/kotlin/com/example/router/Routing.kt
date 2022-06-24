package com.example.plugins

import cn.hutool.extra.qrcode.QrCodeUtil
import cn.hutool.extra.qrcode.QrConfig
import com.example.config.FILE_PATH
import com.example.config.QR_PATH
import com.example.config.STATIC_PATH
import com.example.utils.FileUtils
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.*
import java.util.*


fun Application.configureRouting() {

    routing {
        route("/") {
            get { call.respondText("Hello World!") }
        }

        static("static") {
            staticRootFolder = File(STATIC_PATH)
            files("qrcode")
            files("file")
        }
        route("/qrcode") {
            get {
                val qrcodeString: String? = call.request.queryParameters["url"]
                val fileName = qrcodeString?.let { it1 -> FileUtils.createQRcode(it1) }
                call.respond(mapOf("data" to fileName))
            }
        }

    }
}


