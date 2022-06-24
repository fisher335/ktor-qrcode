package com.example.entity

import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable

/**
 * @author Mae
 * 空值就不用序列化
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
class ResponseResult<T> : Serializable {
    var code: Int
    var msg: String? = null
    var data: Any? = null
        private set

    constructor() {
        this.code = AppHttpCodeEnum.SUCCESS.code
        msg = AppHttpCodeEnum.SUCCESS.msg
    }

    constructor(code: Int, data: T) {
        this.code = code
        this.data = data
    }

    constructor(code: Int, msg: String?, data: T) {
        this.code = code
        this.msg = msg
        this.data = data
    }

    constructor(code: Int, msg: String?) {
        this.code = code
        this.msg = msg
    }

    fun error(code: Int, msg: String?): ResponseResult<*> {
        this.code = code
        this.msg = msg
        return this
    }

    fun ok(code: Int, data: T): ResponseResult<*> {
        this.code = code
        this.data = data
        return this
    }

    fun ok(code: Int, data: T?, msg: String?): ResponseResult<*> {
        this.code = code
        this.data = data
        this.msg = msg
        return this
    }

    fun ok(data: T): ResponseResult<*> {
        this.data = data
        return this
    }

    fun setData(data: T) {
        this.data = data
    }

    companion object {
        fun errorResult(code: Int, msg: String?): ResponseResult<*> {
            val result: ResponseResult<*> = ResponseResult<Any?>()
            return result.error(code, msg)
        }

        fun okResult(): ResponseResult<*> {
            return ResponseResult<Any?>()
        }

        fun okResult(code: Int, msg: String?): ResponseResult<*> {
            val result: ResponseResult<*> = ResponseResult<Any?>()
            return result.ok(code, null, msg)
        }

        fun okResult(data: Any?): ResponseResult<*> {
            val result = setAppHttpCodeEnum(AppHttpCodeEnum.SUCCESS, AppHttpCodeEnum.SUCCESS.msg)
            if (data != null) {
                result.data = data
            }
            return result
        }

        fun errorResult(enums: AppHttpCodeEnum): ResponseResult<*> {
            return setAppHttpCodeEnum(enums, enums.msg)
        }

        fun errorResult(enums: AppHttpCodeEnum, msg: String): ResponseResult<*> {
            return setAppHttpCodeEnum(enums, msg)
        }

        fun setAppHttpCodeEnum(enums: AppHttpCodeEnum): ResponseResult<*> {
            return okResult(enums.code, enums.msg)
        }

        private fun setAppHttpCodeEnum(enums: AppHttpCodeEnum, msg: String): ResponseResult<*> {
            return okResult(enums.code, msg)
        }
    }
}