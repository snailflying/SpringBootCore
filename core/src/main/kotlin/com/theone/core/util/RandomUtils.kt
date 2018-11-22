package com.theone.core.util

import java.util.Random
import java.util.UUID

/**
 * @author Aaron.Liu
 * @email bravoon@126.com
 * @date 20/11/2018 22:11
 * @description
 */
object RandomUtils {

    val ALL_CHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
    val LETTER_CHAR = "abcdefghijkllmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
    val NUMBER_CHAR = "0123456789"

    /**
     * 返回一个定长的随机字符串(只包含大小写字母、数字)
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    fun generateString(length: Int): String {
        val sb = StringBuffer()
        val random = Random()
        for (i in 0 until length) {
            sb.append(ALL_CHAR[random.nextInt(ALL_CHAR.length)])
        }
        return sb.toString()
    }

    /**
     * 返回一个定长的随机纯字母字符串(只包含大小写字母)
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    fun generateMixString(length: Int): String {
        val sb = StringBuffer()
        val random = Random()
        for (i in 0 until length) {
            sb.append(LETTER_CHAR[random.nextInt(LETTER_CHAR.length)])
        }
        return sb.toString()
    }

    /**
     * 返回一个定长的随机纯大写字母字符串(只包含大小写字母)
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    fun generateLowerString(length: Int): String {
        return generateMixString(length).toLowerCase()
    }

    /**
     * 返回一个定长的随机纯小写字母字符串(只包含大小写字母)
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    fun generateUpperString(length: Int): String {
        return generateMixString(length).toUpperCase()
    }

    /**
     * 生成一个定长的纯0字符串
     *
     * @param length 字符串长度
     * @return 纯0字符串
     */
    fun generateZeroString(length: Int): String {
        val sb = StringBuffer()
        for (i in 0 until length) {
            sb.append('0')
        }
        return sb.toString()
    }

    /**
     * 根据数字生成一个定长的字符串，长度不够前面补0
     *
     * @param num        数字
     * @param fixedLenth 字符串长度
     * @return 定长的字符串
     */
    fun toFixedLengthString(num: Long, fixedLenth: Int): String {
        val sb = StringBuffer()
        val strNum = num.toString()
        if (fixedLenth - strNum.length >= 0) {
            sb.append(generateZeroString(fixedLenth - strNum.length))
        } else {
            throw RuntimeException("将数字" + num + "转化为长度为" + fixedLenth
                    + "的字符串发生异常！")
        }
        sb.append(strNum)
        return sb.toString()
    }

    /**
     * 每次生成的len位数都不相同
     *
     * @param param
     * @return 定长的数字
     */
    fun getNotSimple(param: IntArray, len: Int): Int {
        val rand = Random()
        for (i in param.size downTo 2) {
            val index = rand.nextInt(i)
            val tmp = param[index]
            param[index] = param[i - 1]
            param[i - 1] = tmp
        }
        var result = 0
        for (i in 0 until len) {
            result = result * 10 + param[i]
        }
        return result
    }

    /**
     * @return
     */
    fun generateUUID(): String {
        return UUID.randomUUID().toString()
    }

    //    public static void main(String[] args) {
    //        System.out.println("返回一个定长的随机字符串(只包含大小写字母、数字):" + generateString(10));
    //        System.out
    //                .println("返回一个定长的随机纯字母字符串(只包含大小写字母):" + generateMixString(10));
    //        System.out.println("返回一个定长的随机纯大写字母字符串(只包含大小写字母):"
    //                + generateLowerString(10));
    //        System.out.println("返回一个定长的随机纯小写字母字符串(只包含大小写字母):"
    //                + generateUpperString(10));
    //        System.out.println("生成一个定长的纯0字符串:" + generateZeroString(10));
    //        System.out.println("根据数字生成一个定长的字符串，长度不够前面补0:"
    //                + toFixedLengthString(123, 10));
    //        int[] in = {1, 2, 3, 4, 5, 6, 7};
    //        System.out.println("每次生成的len位数都不相同:" + getNotSimple(in, 3));
    //    }
}
