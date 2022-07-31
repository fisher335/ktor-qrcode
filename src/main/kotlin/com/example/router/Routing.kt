package com.example.plugins

import com.example.config.STATIC_PATH
import com.example.utils.FileUtils
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

/**
 * 主要的一些路由配置
 *
 */
fun Application.configureRouting() {

    routing {
        /*主要是用来设置静态文件的*/
        static("/static") {
            staticRootFolder = File(STATIC_PATH)
            files("qrcode")
            files("file")
            resource(".")
        }
    }
}


