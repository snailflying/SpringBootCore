package com.theone.core.controller

import com.theone.core.bean.GenericResponse
import com.theone.core.bean.MultiResponse
import org.apache.commons.logging.LogFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.servlet.ModelAndView

/**
 * @author Aaron.Liu
 * @email bravoon@126.com
 * @date 20/11/2018 22:11
 * @description
 */
abstract class BaseController {

    /**
     * rest api return execute body
     *
     * @param executeBody
     * @return
     */
    protected fun execute(executeBody: ExecuteBody<*>): ResponseEntity<GenericResponse> {
        logger.debug("abs controller execute")
        var responseEntity: ResponseEntity<GenericResponse>
        try {
            val obj = executeBody.execute()
            val response: GenericResponse
            if (obj is MultiResponse) {
                response = GenericResponse.createSuccessResponse(obj.data)
                response.extendData = obj.extendData
            } else {
                response = GenericResponse.createSuccessResponse(obj)
            }

            responseEntity = ResponseEntity(response, HttpStatus.OK)
        } catch (ex: Exception) {
            logger.error(ex)
            responseEntity = ResponseEntity(GenericResponse.createErrorResponse("execute controller error", ex),
                    HttpStatus.INTERNAL_SERVER_ERROR)
            ex.printStackTrace()
        }

        return responseEntity
    }

    /**
     * @param executeBody
     * @return
     */
    protected fun executeView(executeBody: ViewExecuteBody): ModelAndView {
        logger.debug("abs controller execute for view")
        val view = ModelAndView()
        try {
            executeBody.execute(view)
        } catch (ex: Exception) {
            logger.error(ex)
        }

        return view
    }

    companion object {

        private val logger = LogFactory.getLog(BaseController::class.java)
    }
}
