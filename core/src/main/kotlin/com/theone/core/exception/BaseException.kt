package com.theone.core.exception

/**
 * @author Aaron.Liu
 * @email bravoon@126.com
 * @date 20/11/2018 22:11
 * @description
 */
class BaseException : Exception {

    constructor() : super() {}

    constructor(message: String) : super(message) {}


    constructor(message: String, cause: Throwable) : super(message, cause) {}


    constructor(cause: Throwable) : super(cause) {}


    protected constructor(message: String, cause: Throwable,
                          enableSuppression: Boolean,
                          writableStackTrace: Boolean) : super(message, cause, enableSuppression, writableStackTrace) {
    }
}
