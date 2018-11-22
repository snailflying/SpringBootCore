package com.theone.core.util

/**
 * @author Aaron.Liu
 * @email bravoon@126.com
 * @date 20/11/2018 22:11
 * @description
 */
object Preconditions {
    /**
     * 可以判断任何一个对象是否为空,包括List Map String 复杂对象等等,
     * 就是对象是null也是安全的
     *
     * @param t
     * @param <T>
     * @return
    </T> */
    @JvmStatic
    fun <T> isBlank(t: T?): Boolean {

        val result = false

        if (t == null) {
            return true
        }

        if (t is List<*>) {
            if ((t as List<*>).size == 0) {
                return true
            }
        } else if (t is Map<*, *>) {
            if ((t as Map<*, *>).size == 0) {
                return true
            }
        } else if (t is Array<*>) {
            if ((t as Array<Any>).size == 0) {
                return true
            }
        } else if (t is String) {
            val strLen: Int

            strLen = (t as String).length
            if (strLen == 0) {
                return true
            }

            for (i in 0 until strLen) {
                if (Character.isWhitespace(t[i]) == false) {
                    return false
                }
            }

            return true
        }

        return result
    }

    @JvmStatic
    fun <T> isNotBlank(t: T?): Boolean {
        return !isBlank(t)
    }
}
