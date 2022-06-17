package com.example.plugins

import cn.hutool.extra.qrcode.QrCodeUtil
import cn.hutool.extra.qrcode.QrConfig
import com.example.config.QR_PATH
import com.example.config.STATIC_PATH
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
                val qrcodeString: String? = call.request.queryParameters["qrcode"]
                val fileName = qrcodeString?.let { it1 -> createQRcode(it1) }
                call.respond(mapOf("fileName" to fileName))
            }
        }
    }
}

fun createQRcode(content: String): String {
    var config = QrConfig(300, 300)
    // 设置边距，既二维码和背景之间的边距
//    config.setMargin(3);
    // 设置前景色，既二维码颜色（青色）
//    config.setForeColor(Color.BLUE)
//    // 设置背景色（灰色）
//    config.setBackColor(Color.GRAY)
    val uuid = UUID.randomUUID().toString().replace("-", "")
    println("$QR_PATH/$uuid.png")
    QrCodeUtil.generate("nihao", config, File("$STATIC_PATH/qrcode/$uuid.png"))
    return "$uuid.png"
}

