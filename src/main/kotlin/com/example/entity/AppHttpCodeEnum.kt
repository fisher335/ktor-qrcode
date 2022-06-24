package com.example.entity

/**
 * @author Mae
 */
enum class AppHttpCodeEnum(var code: Int, var msg: String) {
    /**
     * 成功
     */
    SUCCESS(200, "操作成功"),

    /**
     * 自定义：最后一个注意用分号
     */
    AUTHORITY_NOT(503, "权限不足");

}