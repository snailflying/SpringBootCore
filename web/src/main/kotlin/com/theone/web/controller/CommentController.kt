package com.theone.web.controller

import com.theone.core.bean.GenericResponse
import com.theone.core.controller.BaseEntityController
import com.theone.core.controller.ExecuteBody
import com.theone.core.exception.BaseException
import com.theone.web.domain.Comment
import com.theone.web.service.CommentService
import com.theone.web.util.RedisDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.io.Serializable
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by aaron on 22/05/2017.
 */
@RestController
@RequestMapping(path = ["/v1/comments"])
class CommentController : BaseEntityController<Comment>() {


    @Autowired
    private val commentService: CommentService? = null

//    @Autowired
//    private lateinit var redisDao: RedisDao

    @RequestMapping(path = ["/user/{userId}"], method = [RequestMethod.GET])
    fun user(request: HttpServletRequest,
             response: HttpServletResponse,
             @PathVariable(value = "userId") userId: Long?): ResponseEntity<GenericResponse> {
        return execute(object : ExecuteBody<Serializable> {
            @Throws(BaseException::class)
            override fun execute(): Serializable? {
                return commentService!!.findByUser(userId) as ArrayList<*>
            }
        })
    }


    @RequestMapping(path = arrayOf("/add"), method = arrayOf(RequestMethod.POST))
    fun add(request: HttpServletRequest,
            response: HttpServletResponse,
            @RequestBody entity: Comment): ResponseEntity<GenericResponse> {
        return execute(object : ExecuteBody<Serializable> {
            @Throws(BaseException::class)
            override fun execute(): Serializable? {

                val user = commentService!!.findByUserId(entity.userId)
                val shop = commentService.findByShopId(entity.shopId)
                entity.user = user
                entity.shop = shop
                return commentService.save(entity)

            }
        })
    }

}
