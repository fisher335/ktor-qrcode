package com.example.router

import com.example.config.FILE_PATH
import com.example.entity.FileInfo
import com.example.utils.FileUtils
import com.example.utils.jsonOk
import com.example.utils.param
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.net.URLEncoder

/**
 * 文件相关的路由设置在扩展方法里面
 *
 */
fun Application.fileRouting() {
    routing {
        get("/file") {
            val search = call.param("search").trim()
            var fileInfos = FileUtils.getFileList(FILE_PATH)
            if (search.isNotEmpty()) {
                fileInfos = fileInfos.filter { it.name.contains(search, ignoreCase = true) } as MutableList<FileInfo>
            }
            call.respond(fileInfos)
        }
        post("/upload") {
            val multipart = call.receiveMultipart()
            val filename = fileSave(multipart)
            call.jsonOk("success", filename)
        }
        get("/filedown/{file}") {
            val fileName: String? = call.parameters["file"]
            val file = File("$FILE_PATH/$fileName")
            call.response.header(
                HttpHeaders.ContentDisposition,
                ContentDisposition.Attachment.withParameter(
                    ContentDisposition.Parameters.FileName,
                    withContext(Dispatchers.IO) {
                        URLEncoder.encode(fileName, "UTF-8")
                    }
                ).toString()
            )
            call.respondFile(file)
        }
    }
}

/**
 *
 *
 * @param multipartData
 * @return fileNAme
 */
suspend fun fileSave(multipartData: MultiPartData): String {
    var fileDescription = ""
    var fileName = ""
    multipartData.forEachPart { part ->
        when (part) {
            is PartData.FormItem -> {
                fileDescription = part.value
            }
            is PartData.FileItem -> {
                fileName = part.originalFileName as String
                withContext(Dispatchers.IO) {
                    val fileBytes = part.streamProvider().readBytes()

                    File("$FILE_PATH/$fileName").writeBytes(fileBytes)
                }
            }
            else -> {}
        }
    }
    return fileName
}
