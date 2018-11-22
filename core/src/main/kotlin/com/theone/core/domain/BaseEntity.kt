package com.theone.core.domain


import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.theone.core.util.Preconditions
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.io.Serializable
import java.util.*
import javax.persistence.*

/**
 * @author Aaron.Liu
 * @email bravoon@126.com
 * @date 20/11/2018 22:11
 * @description
 */
@MappedSuperclass
abstract class BaseEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @JsonProperty("id")
    var id: Long? = null,

    @Column(name = "date_created", nullable = false, updatable = false)
    @JsonProperty("date_created")
    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    var dateCreated: Date? = null,

    @Column(name = "last_updated", nullable = false)
    @JsonProperty("last_updated")
    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    var lastUpdated: Date? = null,

    @Column(name = "deleted", nullable = false)
    @JsonIgnore
    var deleted: Boolean = false,

    //此字段非常重要,不能为空
    @Column(name = "version")
    @JsonProperty("version")
    @Version
    var version: Long? = null

) : Serializable {

    override fun equals(obj: Any?): Boolean {
        return if (Preconditions.isNotBlank(obj) && obj is BaseEntity && Preconditions.isNotBlank(this.id)) {
            this.id!!.equals(obj.id)
        } else false
    }

    override fun hashCode(): Int {
        return this.id?.hashCode() ?: super.hashCode()
    }

    companion object {

        private const val serialVersionUID = -1L
    }

}
