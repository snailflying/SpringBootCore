package com.theone.core.service


import com.theone.core.bean.pageable.PageableRequest
import com.theone.core.bean.pageable.PageableResponse
import com.theone.core.domain.BaseEntity
import com.theone.core.exception.BaseException
import org.apache.commons.logging.LogFactory

/**
 * @author Aaron.Liu
 * @email bravoon@126.com
 * @date 20/11/2018 22:11
 * @description
 */
interface IBaseEntityService<T : BaseEntity> {

    @Throws(BaseException::class)
    operator fun get(id: Long?): T?

    @Throws(BaseException::class)
    fun save(item: T): T

    @Throws(BaseException::class)
    fun list(): List<T>

    @Throws(BaseException::class)
    fun list(pageable: PageableRequest): PageableResponse<T>

    @Deprecated("")
    @Throws(BaseException::class)
    fun delete(id: Long?): Boolean

    @Throws(BaseException::class)
    fun filterEntity(item: T?): Boolean

    companion object {

        val logger = LogFactory.getLog(IBaseEntityService::class.java)
    }
}
