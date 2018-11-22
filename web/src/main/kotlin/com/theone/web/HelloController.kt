package com.theone.web

import com.theone.core.controller.BaseEntityController
import com.theone.web.domain.Comment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController : BaseEntityController<Comment>() {

    @GetMapping(path = ["/hello"])
    fun hello(): String {
        return "hello world!"
    }
}