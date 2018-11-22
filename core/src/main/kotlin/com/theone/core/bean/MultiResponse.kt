package com.theone.core.bean

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @author Aaron.Liu
 * @email bravoon@126.com
 * @date 20/11/2018 22:11
 * @description
 */
class MultiResponse(
        @JsonProperty("data")
        val data: Any? = null,

        @JsonProperty("extend_data")
        val extendData: Any? = null
) : BaseBean()