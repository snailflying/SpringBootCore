package com.theone.web.domain

import com.fasterxml.jackson.annotation.JsonProperty
//import com.google.gson.annotations.SerializedName
import com.theone.core.domain.BaseEntity

import javax.persistence.*

/**
 * Created by aaron on 22/05/2017.
 */
@Entity
@Table(name = "comment")
class Comment(

//        @SerializedName("user_id")
        @JsonProperty("user_id")
        @Transient
        var userId: Long? = null,

//        @SerializedName("shop_id")
        @JsonProperty("shop_id")
        @Transient
        var shopId: Long? = null,

        @Column(name = "img")
//        @SerializedName("img")
        @JsonProperty("img")
        var img: String? = null,

        @Column(name = "content")
//        @SerializedName("content")
        @JsonProperty("content")
        var content: String? = null,

//        @SerializedName("user")
        @JsonProperty("user")
        @ManyToOne
        @JoinColumn(name = "user_id", referencedColumnName = "id")
        var user: User? = null,

//        @SerializedName("shop")
        @JsonProperty("shop")
        @ManyToOne
        @JoinColumn(name = "shop_id", referencedColumnName = "id")
        var shop: Shop? = null,


        @Column(name = "star")
//        @SerializedName("star")
        @JsonProperty("star")
        var star: Int = 0
) : BaseEntity() {

    //    @Column(name = "user_id")
    //    @JSONField(name = "user_id")
    //    @JsonProperty("user_id")
    //    Long userId;
    //
    //
    //    @Column(name = "shop_id")
    //    @JSONField(name = "shop_id")
    //    @JsonProperty("shop_id")
    //    Long shopId;


}
