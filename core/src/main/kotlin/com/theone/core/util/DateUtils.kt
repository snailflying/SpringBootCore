package com.theone.core.util

import java.text.SimpleDateFormat
import java.util.Date

/**
 * @author Aaron.Liu
 * @email bravoon@126.com
 * @date 20/11/2018 22:11
 * @description
 */
object DateUtils {

    val FORMAT_Y = "yyyy"
    val FORMAT_D_1 = "yyyy/MM/dd"
    val FORMAT_D_2 = "yyyy-MM-dd"
    val FORMAT_D_3 = "yyyyMMdd"
    val FORMAT_D = "dd"
    val FORMAT_DT_1 = "yyyy/MM/dd HH:mm:ss"
    val FORMAT_DT_2 = "yyyy-MM-dd HH:mm:ss"
    val FORMAT_M_1 = "yyyy/MM"
    val FORMAT_M_2 = "yyyy-MM"
    val FORMAT_M_3 = "yyyyMM"
    val FORMAT_M = "MM"
    val FORMAT_MD_1 = "MM/dd"
    val FORMAT_MD_2 = "MM-dd"
    val FORMAT_MD_3 = "MMdd"
    val FORMAT_T_1 = "HH:mm:ss"
    val FORMAT_T_2 = "HH:mm"
    val FORMAT_TH = "HH"
    val FORMAT_TM = "mm"
    val FORMAT_TS = "ss"

    fun now(): Date {
        return Date()
    }

    fun today(): String {
        val sdf = SimpleDateFormat(FORMAT_D_3)
        return sdf.format(now())
    }

    fun hour(): String {
        val sdf = SimpleDateFormat(FORMAT_TH)
        return sdf.format(now())
    }


    fun minute(): String {
        val sdf = SimpleDateFormat(FORMAT_TM)
        return sdf.format(now())
    }


    fun second(): String {
        val sdf = SimpleDateFormat(FORMAT_TS)
        return sdf.format(now())
    }

    fun month(): String {
        val sdf = SimpleDateFormat(FORMAT_M)
        return sdf.format(now())
    }


    fun year(): String {
        val sdf = SimpleDateFormat(FORMAT_Y)
        return sdf.format(now())
    }


    fun day(): String {
        val sdf = SimpleDateFormat(FORMAT_D)
        return sdf.format(now())
    }


    fun format(date: Date, format: String): String {
        val sdf = SimpleDateFormat(format)
        return sdf.format(date)
    }
}
