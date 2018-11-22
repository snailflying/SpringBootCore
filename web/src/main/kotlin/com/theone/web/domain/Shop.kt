package com.theone.web.domain

import com.fasterxml.jackson.annotation.JsonProperty
//import com.google.gson.annotations.SerializedName
import com.theone.core.domain.BaseEntity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

/**
 * Created by aaron on 22/05/2017.
 */
@Entity
@Table(name = "shop")
class Shop(
        @Column(name = "shop_name")
//        @SerializedName("title")
        @JsonProperty("title")
        var shopName: String? = null,

        @Column(name = "shop_desc")
//        @SerializedName("desc")
        @JsonProperty("desc")
        var shopDesc: String? = null,

        @Column(name = "logo")
//        @SerializedName("img")
        @JsonProperty("img")
        var logo: String? = null,

        @Column(name = "replay_content")
//        @SerializedName("replay_content")
        @JsonProperty("replay_content")
        var replayContent: String? = null,

        @Column(name = "replay_img")
//        @SerializedName("replay_img")
        @JsonProperty("replay_img")
        var replayImg: String? = null) : BaseEntity()
