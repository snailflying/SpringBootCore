package com.theone.core.util

import java.math.BigDecimal
import java.util.Random
import java.util.UUID

/**
 * @author Aaron.Liu
 * @email bravoon@126.com
 * @date 20/11/2018 22:11
 * @description
 */
object KeyGenerator {

    /**
     * 获得
     *
     * @param length
     * @return
     */
    fun genToken(length: Int): String? {
        if (length <= 0) {
            return null
        }
        var result = ""
        for (i in 0 until length) {
            val isChar = Math.random() >= 10.0 / (10 + 26)
            val intVal: Int
            if (isChar) {
                intVal = (Math.random() * 26 + 97).toInt()
            } else {
                intVal = (Math.random() * 10 + 48).toInt()
            }
            result = result + intVal.toChar()
        }
        return result.toUpperCase()
    }


    /**
     * only number
     *
     * @param length
     * @return
     */
    fun genCode(length: Int): String? {
        if (length <= 0) {
            return null
        }
        val sb = StringBuffer()
        for (i in 0 until length) {
            sb.append((Math.random() * 10).toInt().toString())
        }
        return sb.toString()
    }

    /**
     * 生成 32位 UUID 不带小横线
     * 大写
     *
     * @return
     */
    fun genUUID2(): String {
        return genUUID().replace("-".toRegex(), "")
    }

    /**
     * 生成 32为 UUID 带小横线
     * 大写
     *
     * @return
     */
    fun genUUID(): String {
        return UUID.randomUUID().toString().toUpperCase()
    }

    /**
     * @param number
     * @param decimal
     * @return
     */
    fun round(number: Double, decimal: Int): String {
        return BigDecimal(number).setScale(decimal, BigDecimal.ROUND_HALF_UP).toString()
    }


    /**
     * @param min
     * @param max
     * @return
     */
    fun random(min: Int, max: Int): Int {
        val seed: Long = 1024
        return Random(seed).nextInt(max - min) + min
    }

}
