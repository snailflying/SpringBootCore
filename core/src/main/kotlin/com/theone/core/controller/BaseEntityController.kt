package com.theone.core.controller


import com.theone.core.bean.GenericResponse
import com.theone.core.bean.pageable.PageableRequest
import com.theone.core.bean.pageable.PageableResponse
import com.theone.core.domain.BaseEntity
import com.theone.core.exception.BaseException
import com.theone.core.service.IBaseEntityService
import com.theone.core.util.Preconditions
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import java.io.Serializable
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author Aaron.Liu
 * @email bravoon@126.com
 * @date 20/11/2018 22:11
 * @description
 */
abstract class BaseEntityController<T : BaseEntity> : BaseController() {

    @Autowired
    protected lateinit var baseEntityService: IBaseEntityService<T>

    /**
     * GET /products：列出所有产品
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(path = ["/list"], method = [(RequestMethod.GET)])
    fun list(request: HttpServletRequest,
             response: HttpServletResponse): ResponseEntity<GenericResponse> {
        return execute(object : ExecuteBody<Serializable> {
            @Throws(BaseException::class)
            override fun execute(): Serializable {
                val list = baseEntityService.list()
                val pageableResponse = PageableResponse<T>()
                pageableResponse.list = (list)
                pageableResponse.totalCount = (if (Preconditions.isNotBlank(list)) list.size.toLong() else 0)
                pageableResponse.totalPages = (if (Preconditions.isNotBlank(list)) 1 else 0)
                return pageableResponse
            }
        })
    }


    @RequestMapping(path = ["/list"], method = [(RequestMethod.POST)])
    fun listPageable(request: HttpServletRequest,
                     response: HttpServletResponse,
                     @RequestBody pageableRequest: PageableRequest): ResponseEntity<GenericResponse> {
        return execute(object : ExecuteBody<Serializable> {
            @Throws(BaseException::class)
            override fun execute(): Serializable {
                return baseEntityService.list(pageableRequest)
            }
        })
    }

    /**
     * POST /products：新建一个App
     * PUT /products/ID：更新某个指定App的信息（提供该App的全部信息）
     * PATCH /products/ID：更新某个指定App的信息（提供该App的部分信息）
     *
     *
     * 包含id将 做更新操作
     * 没有id 新建对象
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(path = ["", "/"], method = [(RequestMethod.POST)])
    fun post(request: HttpServletRequest,
             response: HttpServletResponse,
             @RequestBody entity: T): ResponseEntity<GenericResponse> {
        logger.debug("post")
        return execute(object : ExecuteBody<Serializable> {
            @Throws(BaseException::class)
            override fun execute(): T {
                return baseEntityService.save(entity)
            }
        })
    }

    /**
     * GET /products/ID：获取某个指定Item的信息
     *
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping(path = ["/{id}"], method = [(RequestMethod.GET)])
    operator fun get(request: HttpServletRequest,
                     response: HttpServletResponse,
                     @PathVariable(value = "id") id: Long?): ResponseEntity<GenericResponse> {
        logger.debug("get")
        return execute(object : ExecuteBody<Serializable> {
            @Throws(BaseException::class)
            override fun execute(): T? {
                return baseEntityService.get(id)
            }
        })

    }


    /**
     * DELETE /products/ID：删除某个产品
     *
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping(path = ["/{id}"], method = [(RequestMethod.DELETE)])
    fun del(request: HttpServletRequest,
            response: HttpServletResponse,
            @PathVariable(value = "id") id: Long?): ResponseEntity<GenericResponse> {
        return execute(object : ExecuteBody<Serializable> {
            @Throws(BaseException::class)
            override fun execute(): Serializable {
                return baseEntityService.delete(id)
            }
        })
    }

    companion object {

        private val logger = LogFactory.getLog(BaseEntityController::class.java)
    }
}
