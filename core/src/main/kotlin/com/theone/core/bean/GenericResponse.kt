package com.theone.core.bean

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.theone.core.util.Preconditions

/**
 * @author Aaron.Liu
 * @email bravoon@126.com
 * @date 20/11/2018 22:11
 * @description
 */
class GenericResponse(
        @JsonProperty("code")
        var code: Int = 0,       //执行状态

        @JsonProperty("message")
        var message: String? = null, //消息摘要

        @JsonProperty("detail")
        @JsonIgnore
        var detail: String? = null,  //详细描述 stacktrace

        @JsonProperty("data")
        var data: Any? = null,   //执行结果


        @JsonProperty("extend_data")
        @JsonIgnore
        var extendData: Any? = null    //执行结果
) : BaseBean() {

    companion object {

        val STATUS_OK = 0
        val STATUS_ERROR = -1


        /**
         * 创建一个成功的返回
         *
         * @param data
         * @return
         */
        fun createSuccessResponse(data: Any?): GenericResponse {
            val response = GenericResponse()
            response.code = STATUS_OK
            response.data = data
            return response
        }

        /**
         * 创建一个错误返回Response
         *
         * @param message
         * @param ex
         * @return
         */
        @JvmOverloads
        fun createErrorResponse(message: String, ex: Exception? = null): GenericResponse {
            val response = GenericResponse()
            response.code = STATUS_ERROR
            response.message = message
            response.detail = (if (Preconditions.isNotBlank<Exception>(ex)) ex!!.toString() else null)
            return response
        }
    }

}
/**
 * 创建一个错误返回Response
 *
 * @param message
 * @return
 */
