package com.theone.web.repository


import com.theone.core.repository.BaseEntityRepository
import com.theone.web.domain.Comment
import com.theone.web.domain.User

/**
 * Created by aaron on 22/05/2017.
 */
interface CommentRepository : BaseEntityRepository<Comment> {

    fun findByUserAndDeleted(user: User?, deleted: Boolean): List<Comment>
}
