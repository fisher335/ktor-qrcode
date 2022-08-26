package com.example.plugins

import com.example.api.fileRouting
import com.example.api.qrRouting
import com.example.api.testRouting
import com.example.config.STATIC_PATH
import io.ktor.server.application.*
import io.ktor.server.http.content.*
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
        //文件上传相关路由
        fileRouting()
        //二维码相关路由
        qrRouting()
        //测试路由
        testRouting()
    }
}


