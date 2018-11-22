package com.theone.core.util

import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author Aaron.Liu
 * @email bravoon@126.com
 * @date 20/11/2018 22:11
 * @description
 */
object CookieUtils {

    /**
     * @param request
     * @param response
     * @param cookieName
     * @return
     */
    fun setCookieIfAbsent(request: HttpServletRequest,
                          response: HttpServletResponse,
                          cookieName: String): String {

        val theCookie = extractCookie(request, cookieName)

        if (Preconditions.isNotBlank<Cookie>(theCookie)) {
            return theCookie!!.value
        }
        val cookieValue = RandomUtils.generateUUID()
        val cookie = Cookie(cookieName, cookieValue)
        cookie.path = "/"
        cookie.maxAge = Integer.MAX_VALUE - 8
        response.addCookie(cookie)
        return cookieValue
    }

    /**
     * @param request
     * @param cookieName
     * @return
     */
    fun extractCookie(request: HttpServletRequest, cookieName: String): Cookie? {
        val cookies = request.cookies
        var theCookie: Cookie? = null
        if (Preconditions.isNotBlank(cookies)) {
            for (cookie in cookies) {
                if (cookie.name == cookieName) {
                    theCookie = cookie
                    break
                }
            }
        }
        return theCookie
    }

    /**
     * @param request
     * @param response
     * @param cookieName
     * @param cookieValue
     * @param cookieTtl   in second
     */
    fun overwriteCookie(request: HttpServletRequest,
                        response: HttpServletResponse,
                        cookieName: String,
                        cookieValue: String,
                        cookieTtl: Int) {
        val cookie = Cookie(cookieName, cookieValue)
        cookie.path = "/"
        cookie.maxAge = cookieTtl
        response.addCookie(cookie)
    }

}
