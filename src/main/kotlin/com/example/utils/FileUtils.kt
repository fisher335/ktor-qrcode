package com.example.utils

import cn.hutool.core.date.DateUtil
import cn.hutool.core.io.FileUtil
import cn.hutool.extra.qrcode.QrCodeUtil
import cn.hutool.extra.qrcode.QrConfig
import com.example.config.QR_PATH
import com.example.entity.FileInfo
import java.io.File
import java.text.DecimalFormat
import java.util.*

/**
 **
 * @Description: 静态工具类
 * @Param:
 * @return:
 * @Author: phoenix
 * @Date: 2022/6/27
 */
object FileUtils {

    fun getSize(i: Long): String {
        var result = ""
        val kb: Long = 1024
        val mb = kb * 1024
        val gb = mb * 1024

        /*实现保留小数点两位*/
        val df = DecimalFormat("#.00")
        result = if (i >= gb) {
            df.format((i.toFloat() / gb).toDouble()) + "GB"
        } else if (i >= mb) {
            df.format((i.toFloat() / mb).toDouble()) + "MB"
        } else if (i >= kb) {
            String.format("%.2f", i.toFloat() / kb) + "KB"
        } else {
            "$i B"
        }
        return result
    }

    fun createQRcode(url: String): String {
        var config = QrConfig(300, 300)
        /*
         设置边距，既二维码和背景之间的边距
            config.setMargin(3);
         设置前景色，既二维码颜色（青色）
            config.setForeColor(Color.BLUE)
           设置背景色（灰色）
            config.setBackColor(Color.GRAY)
        */
        val uuid = UUID.randomUUID().toString().replace("-", "")
        println("$QR_PATH/$uuid.png")
        QrCodeUtil.generate(url, config, File("$QR_PATH/$uuid.png"))
        return "$uuid.png"
    }

    fun getFileList(path: String): MutableList<FileInfo> {
        val files = FileUtil.loopFiles(path)
        var fileInfoList = mutableListOf<FileInfo>()
        for (file in files) {
            val fileOne = FileInfo(file.name)
            fileOne.date = DateUtil.date(file.lastModified()).toString()
            fileOne.size = getSize(file.length())
            fileOne.url = "/${file.name}"
            fileInfoList.add(fileOne)
        }
        return fileInfoList
    }


}

fun getFileSequence(path: String, p: (File) -> Boolean): Sequence<File> {
    val f = File(path)
    return f.walk().filter(p)
}

fun main() {
    val a = getFileSequence("d:/temp") { it.isFile }
    a.forEach { file -> println(file.name + file.lastModified() + file.extension + file.length()) }
}