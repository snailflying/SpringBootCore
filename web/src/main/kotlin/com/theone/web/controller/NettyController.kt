package com.theone.web.controller

import com.theone.core.bean.GenericResponse
import com.theone.core.controller.BaseController
import com.theone.core.controller.ExecuteBody
import com.theone.core.exception.BaseException
import com.theone.core.util.RandomUtils
import com.theone.web.netty.NettyServerHandler
import com.theone.web.proto.OneTestProto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.io.Serializable
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by aaron on 23/05/2017.
 */
@RestController
@RequestMapping(path = ["/v1/netty"])
class NettyController : BaseController() {

    @Autowired
    private lateinit var nettyServerHandler: NettyServerHandler

    @RequestMapping(path = ["/send"], method = [RequestMethod.POST])
    fun send(request: HttpServletRequest,
            response: HttpServletResponse): ResponseEntity<GenericResponse> {
        return execute(object : ExecuteBody<Serializable> {
            @Throws(BaseException::class)
            override fun execute(): Serializable? {

                val channels = nettyServerHandler.channels
                val uName = RandomUtils.generateString(5)
                System.out.print("aaron uname$uName channels num:${channels.size}")
                for (channel in channels) {
                    val res = OneTestProto.OneTest.newBuilder()
                            .setTestId(1)
                            .setTitle("aaron [用户]:$uName")
                            .setContent("aaron content:[用户]-$uName send a msg.")
                            .build()
                    System.out.print("aaron res:$res")

                    channel.writeAndFlush(res)
                }
                return "success la"
            }
        })
    }


}
