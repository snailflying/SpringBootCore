package com.theone.web.controller

import com.theone.core.controller.BaseEntityController
import com.theone.web.domain.Shop
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by aaron on 23/05/2017.
 */
@RestController
@RequestMapping(path = ["/v1/shop"])
class ShopController : BaseEntityController<Shop>()
