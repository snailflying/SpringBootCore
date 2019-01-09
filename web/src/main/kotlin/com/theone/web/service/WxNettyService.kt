package com.theone.web.service

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelFuture
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.handler.logging.LogLevel
import io.netty.handler.logging.LoggingHandler
//import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import com.theone.web.netty.NettyServerFilter






/**
 * @Author Aaron
 * @Date 2018-12-29
 * @Email aaron@magicwindow.cn
 * @Description
 */
@Service("nettyService")
class WxNettyService {
    @Value("\${netty.port}")
    private val port: Int = 0
    //    @Autowired
//    private var mServerBootstrap: ServerBootstrap? = null
//    private lateinit var mWorkerGroup: EventLoopGroup
    private val boss = NioEventLoopGroup()
    private val work = NioEventLoopGroup()
    private lateinit var channelFuture: ChannelFuture
    private var isInit: Boolean = false


    @Autowired
    private val nettyServerFilter: NettyServerFilter? = null

    fun run() {
        if (isInit) {
            return
        }
        //创建worker线程池，这里只创建了一个线程池，使用的是netty的多线程模型
//        mWorkerGroup = NioEventLoopGroup()
        //服务端启动引导类，负责配置服务端信息
        val mServerBootstrap = ServerBootstrap()
        try {
            mServerBootstrap.group(boss, work)
                    .channel(NioServerSocketChannel::class.java)
                    .handler(LoggingHandler(LogLevel.INFO))
                    .childHandler(nettyServerFilter)

            channelFuture = mServerBootstrap.bind(port).sync()
            channelFuture.channel().closeFuture().sync()
            isInit = true
        } catch (e: InterruptedException) {
            e.printStackTrace();
        }
//        finally {
//            // 关闭EventLoopGroup，释放掉所有资源包括创建的线程
//            work.shutdownGracefully();
//            boss.shutdownGracefully();
//        }

    }

//    fun shutDown() {
//        if (channelFuture != null && channelFuture.isSuccess()) {
//            isInit = false
//            channelFuture.channel().closeFuture()
//            work.shutdownGracefully()
//            boss.shutdownGracefully()
//        }
//    }

}
