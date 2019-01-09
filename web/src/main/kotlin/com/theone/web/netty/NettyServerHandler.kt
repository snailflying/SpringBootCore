package com.theone.web.netty

import com.theone.core.util.RandomUtils
import com.theone.web.proto.OneTestProto
import com.theone.web.util.RedisDao
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.channel.group.ChannelGroup
import io.netty.channel.group.DefaultChannelGroup
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame
import io.netty.util.concurrent.GlobalEventExecutor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


/**
 * @Author Aaron
 * @Date 2018-12-29
 * @Email aaron@magicwindow.cn
 * @Description
 */
@Service("nettyServerHandler")
@ChannelHandler.Sharable
class NettyServerHandler : SimpleChannelInboundHandler<OneTestProto.OneTest>() {

    private val TAG = "ServerChannelHandler"
    var channels: ChannelGroup = DefaultChannelGroup(GlobalEventExecutor.INSTANCE)

    @Autowired
    private lateinit var redisDao: RedisDao

    @Throws(Exception::class)
    override fun channelRead0(channelHandlerContext: ChannelHandlerContext, test: OneTestProto.OneTest) {
        val res = OneTestProto.OneTest.newBuilder()
                .setTestId(test.getTestId())
                .setTitle("aaron title:" + test.getTitle())
                .setContent("aaron content:" + test.getContent())
                .build()
        channelHandlerContext.writeAndFlush(res)
    }


    @Throws(Exception::class)
    override fun handlerAdded(ctx: ChannelHandlerContext?) {
        println(ctx!!.channel().remoteAddress())
        val uName = RandomUtils.generateString(5)  //用来获取一个随机的用户名，可以用其他方式代替

        val incoming = ctx.channel()
        for (channel in channels) {
            val res = OneTestProto.OneTest.newBuilder()
                    .setTestId(1)
                    .setTitle("aaron title:$uName")
                    .setContent("aaron content:[新用户] - $uName 加入")
                    .build()
            channel.writeAndFlush(res)
        }
        redisDao.saveString(incoming.id().toString() + "", uName)   //存储用户
        channels.add(ctx.channel())
    }

    @Throws(Exception::class)
    override fun handlerRemoved(ctx: ChannelHandlerContext?) {
        val incoming = ctx!!.channel()
        val uName = redisDao.getString(incoming.id().toString())?:""
        for (channel in channels) {
            val res = OneTestProto.OneTest.newBuilder()
                    .setTestId(1)
                    .setTitle("aaron title:$uName")
                    .setContent("aaron content:[新用户] - $uName 离开")
                    .build()
            channel.writeAndFlush(res)
        }
        redisDao.deleteString(incoming.id().toString())   //删除用户
        redisDao.saveString("cacheName", redisDao.getString("cacheName")?.replace(uName, "")?:"")   //标准已经使用的用户名
        channels.remove(ctx.channel())
    }

    @Throws(Exception::class)
    override fun channelActive(ctx: ChannelHandlerContext) {
        val incoming = ctx.channel()
        System.out.println("aaron 用户:" + redisDao.getString(incoming.id().toString() + "") + "在线")
    }


    @Throws(Exception::class)
    override fun channelInactive(ctx: ChannelHandlerContext) {
        val incoming = ctx.channel()
        System.out.println("aaron 用户:" + redisDao.getString(incoming.id().toString() + "") + "掉线")
    }

    @Throws(Exception::class)
    override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
        val incoming = ctx.channel()
        System.out.println("aaron用户:" + redisDao.getString(incoming.id().toString() + "") + "异常")
        cause.printStackTrace()
        ctx.close()
    }

}