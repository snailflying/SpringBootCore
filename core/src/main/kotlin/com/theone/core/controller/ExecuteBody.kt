package com.theone.core.controller

import java.io.Serializable

/**
 * @author Aaron.Liu
 * @email bravoon@126.com
 * @date 20/11/2018 22:11
 * @description
 */
interface ExecuteBody<T : Serializable> {

    @Throws(Exception::class)
    fun execute(): T?
}
