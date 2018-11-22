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
@Table(name = "user")
class User(@Column(name = "user_name")
//           @SerializedName("user_name")
           @JsonProperty("user_name")
           val userName: String? = null,

           @Column(name = "avatar")
//           @SerializedName("avatar")
           @JsonProperty("avatar")
           val avatar: String? = null) : BaseEntity()
