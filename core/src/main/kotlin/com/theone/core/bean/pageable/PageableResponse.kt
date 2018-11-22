package com.theone.core.bean.pageable

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.theone.core.bean.BaseBean

import java.io.Serializable

/**
 * @author Aaron.Liu
 * @email bravoon@126.com
 * @date 20/11/2018 22:11
 * @description
 */
class PageableResponse<T : Serializable>(

        @JsonProperty("total_count")
        @JsonIgnore
        var totalCount: Long = 0,

        @JsonProperty("total_pages")
        @JsonIgnore
        var totalPages: Int = 0,

        @JsonProperty("list")
        var list: List<T>? = null
) : BaseBean()
