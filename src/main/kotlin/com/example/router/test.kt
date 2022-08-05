package com.example.router

import cn.hutool.json.JSONUtil


fun main() {
    val jsons = "{url:111111}"
    var jo = JSONUtil.parse(jsons)
    println(jo.getByPath("url"))
}
