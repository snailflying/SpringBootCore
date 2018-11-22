package com.theone.core.util

import javax.servlet.http.HttpServletRequest

/**
 * @author Aaron.Liu
 * @email bravoon@126.com
 * @date 20/11/2018 22:11
 * @description
 */
object UAUtils {

    fun getUA(request: HttpServletRequest): String {
        return request.getHeader("User-Agent")
    }
}
