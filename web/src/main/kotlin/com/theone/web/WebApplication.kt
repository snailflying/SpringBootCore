package com.theone.web

import com.theone.web.service.WxNettyService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
 class WebApplication

fun main(args: Array<String>) {

//    runApplication<WebApplication>(*args)
    val context = runApplication<WebApplication>(*args)
    val nettyServer = context.getBean(WxNettyService::class.java)
    nettyServer.run()
}
