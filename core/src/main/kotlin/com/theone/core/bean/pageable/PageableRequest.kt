package com.theone.core.bean.pageable

import com.fasterxml.jackson.annotation.JsonProperty
import com.theone.core.bean.BaseBean
import com.theone.core.util.Preconditions
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import java.util.*

/**
 * @author Aaron.Liu
 * @email bravoon@126.com
 * @date 20/11/2018 22:11
 * @description
 */
class PageableRequest(
    @JsonProperty("page_number")
    val pageNumber: Int = 0, // zero com.theone.core

    @JsonProperty("page_size")
    val pageSize: Int = 0,


    @JsonProperty("sort_fields")
    val sortFields: List<SortField>? = null
) : BaseBean() {


    fun toPageRequest(): PageRequest {
        var sort: Sort? = null
        if (Preconditions.isNotBlank<List<SortField>>(sortFields)) {
            val orders = ArrayList<Sort.Order>()
            for (sf in sortFields!!) {
                val direction = if (sf.direction === SortDirection.ASC) Sort.Direction.ASC else Sort.Direction.DESC
                val order = Sort.Order(direction, sf.fieldName)
                orders.add(order)
            }
            sort = Sort.by(orders)
        }
        return if (Preconditions.isNotBlank<Sort>(sort)) PageRequest.of(pageNumber, pageSize, sort!!) else PageRequest(
            pageNumber,
            pageSize
        )
    }
}
