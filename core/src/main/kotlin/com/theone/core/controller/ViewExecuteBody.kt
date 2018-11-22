package com.theone.core.controller

import org.springframework.web.servlet.ModelAndView

/**
 * @author Aaron.Liu
 * @email bravoon@126.com
 * @date 20/11/2018 22:11
 * @description
 */
interface ViewExecuteBody {

    @Throws(Exception::class)
    fun execute(view: ModelAndView)
}
