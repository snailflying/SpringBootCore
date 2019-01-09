package com.theone.core.service


import com.theone.core.bean.pageable.PageableRequest
import com.theone.core.bean.pageable.PageableResponse
import com.theone.core.domain.BaseEntity
import com.theone.core.exception.BaseException
import com.theone.core.repository.BaseEntityRepository
import com.theone.core.util.Preconditions
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import javax.transaction.Transactional

/**
 * @author Aaron.Liu
 * @email bravoon@126.com
 * @date 20/11/2018 22:11
 * @description
 */

open abstract class BaseEntityService<T : BaseEntity> : IBaseEntityService<T> {

    @Autowired
    protected var baseEntityRepository: BaseEntityRepository<T>? = null

    /**
     * 获得对象
     *
     * @param id
     * @return
     * @throws BaseException
     */
    @Throws(BaseException::class)
    override fun get(id: Long?): T? {
        return encapsulateEntireEntity(id)
    }

    @Transactional
    @Throws(BaseException::class)
    override fun save(item: T): T {
        logger.debug("save")
        return baseEntityRepository!!.saveAndFlush(item)
    }

    /**
     * @param id
     * @return
     * @throws BaseException
     */
    @Throws(BaseException::class)
    override fun delete(id: Long?): Boolean {
        return false
    }


    /**
     * @return
     * @throws BaseException
     */
    @Throws(BaseException::class)
    override fun list(): List<T> {
        return fillTransientProperties(baseEntityRepository!!.findByDeleted(false))
    }

    /**
     * @param pageable
     * @return
     * @throws BaseException
     */
    @Throws(BaseException::class)
    override fun list(pageable: PageableRequest): PageableResponse<T> {
        val page = baseEntityRepository!!.findByDeleted(false, pageable.toPageRequest())
        val response = PageableResponse<T>()
        response.list = fillTransientProperties(page.content)
        response.totalCount = page.totalElements
        response.totalPages = page.totalPages
        return response
    }

    /**
     * @param item
     * @return true need netty   default false
     * @throws BaseException
     */
    @Throws(BaseException::class)
    override fun filterEntity(item: T?): Boolean {
        return Preconditions.isBlank(item) || Preconditions.isNotBlank(item) && item!!.deleted
    }

    /**
     * 设置缓存中需要的对象
     *
     * @param item
     * @return
     */
    @Throws(BaseException::class)
    protected fun setTransientProperties(item: T) {
        // 不需要存入数据库的字段在这个方法里面设置
    }

    @Throws(BaseException::class)
    protected fun fillTransientProperties(list: List<T>): List<T> {
        if (Preconditions.isNotBlank(list)) {
            for (t in list) {
                setTransientProperties(t)
            }
        }
        return list
    }


    /**
     * 组织整个Entity, 不取缓存的时候才会执行这个方法
     *
     * @param id
     * @return
     */
    @Throws(BaseException::class)
    protected fun encapsulateEntireEntity(id: Long?): T? {
        if (id == null) {
            return null
        }
        logger.debug("get item without cache")
        var item: T? = baseEntityRepository!!.findById(id).get()
        if (Preconditions.isBlank(item)) {
            throw BaseException("not found item with id {}: " + id!!)
        }
        if (item != null) {
            setTransientProperties(item)
        }
        if (filterEntity(item)) {
            item = null
        }
        return item
    }

    /**
     * @param item
     */
    @Throws(BaseException::class)
    fun syncOtherCaches(item: T) {
        // need override in subclass
    }

    companion object {

        private val logger = LogFactory.getLog(BaseEntityService::class.java)
    }


}
