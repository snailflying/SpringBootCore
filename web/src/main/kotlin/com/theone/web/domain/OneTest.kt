package com.theone.web.domain

import com.fasterxml.jackson.annotation.JsonProperty
import com.theone.core.domain.BaseEntity
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Transient

/**
 * @Author Aaron
 * @Date 2018-12-29
 * @Email aaron@magicwindow.cn
 * @Description
 */
@Entity
@Table(name = "one_test")
class OneTest : BaseEntity() {
    @JsonProperty("test_id")
    @Transient
    var testId: Int = 0

    @JsonProperty("title")
    @Transient
    var title: String? = null

    @JsonProperty("content")
    @Transient
    var content: String? = null
}