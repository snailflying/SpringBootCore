package com.theone.web.service

import com.theone.core.exception.BaseException
import com.theone.core.service.BaseEntityService
import com.theone.web.domain.Comment
import com.theone.web.domain.Shop
import com.theone.web.domain.User
import com.theone.web.repository.CommentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by aaron on 22/05/2017.
 */
@Service
class CommentService : BaseEntityService<Comment>() {

    @Autowired
    private val commentRepository: CommentRepository? = null

    @Autowired
    private val userService: UserService? = null

    @Autowired
    private val shopService: ShopService? = null

    @Throws(BaseException::class)
    fun findByUser(userId: Long?): List<Comment> {
        val user = userService!![userId]
        return commentRepository!!.findByUserAndDeleted(user, false)
    }

    @Throws(BaseException::class)
    fun findByUserId(userId: Long?): User? {
        return userService!![userId]
    }

    @Throws(BaseException::class)
    fun findByShopId(shopId: Long?): Shop? {
        return shopService!![shopId]
    }
}
