package com.theone.core.bean.pageable

import com.fasterxml.jackson.annotation.JsonProperty
import com.theone.core.bean.BaseBean

/**
 * @author Aaron.Liu
 * @email bravoon@126.com
 * @date 20/11/2018 22:11
 * @description
 */
data class SortField(
        @JsonProperty("direction")
        val direction: SortDirection = SortDirection.ASC,
        @JsonProperty("field_name")
        var fieldName: String) : BaseBean()
