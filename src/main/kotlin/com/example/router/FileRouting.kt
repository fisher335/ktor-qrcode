package com.example.router

import cn.hutool.core.date.DateUtil
import cn.hutool.core.io.FileUtil
import com.example.config.FILE_PATH
import com.example.entity.File
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.fileRouting() {
    routing {
        route("/file") {
            get {
                val fileInfos = getFileList(FILE_PATH)
                call.respond(fileInfos)
            }
        }
    }
}


fun getFileList(path: String): MutableList<File> {
    val files = FileUtil.loopFiles(path)
    var filelist = mutableListOf<File>()
    for (file in files) {
        var fileone = File(file.name, DateUtil.date(file.lastModified()).toString(), file.length().toString(), "/${file.name}")
        filelist.add(fileone)
    }
    return filelist
}

