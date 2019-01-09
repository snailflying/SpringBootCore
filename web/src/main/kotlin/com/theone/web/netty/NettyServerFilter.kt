package com.theone.web.netty

import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


/**
 * @Author Aaron
 * @Date 2018-12-29
 * @Email aaron@magicwindow.cn
 * @Description
 */

@Component
@ChannelHandler.Sharable
class NettyServerFilter : ChannelInitializer<SocketChannel>() {

    @Autowired
    private val nettyServerHandler: NettyServerHandler? = null

    override fun initChannel(ch: SocketChannel?) {

        //为连接上来的客户端设置pipeline
        val pipeline = ch?.pipeline()
        pipeline?.run {
            addLast(ProtobufEncoder())
            addLast(ProtobufDecoder())
            addLast("handler", nettyServerHandler)
        }

        //                        pipeline.addLast("decoder", new ProtobufDecoder(Test.ProtoTest.getDefaultInstance()));
        //                        pipeline.addLast("encoder", new ProtobufEncoder());
        //                        pipeline.addLast("decoder", new ObjectDecoder(1024, ClassResolvers.cacheDisabled(this
        //                                .getClass().getClassLoader())));

        //入参说明: 读超时时间、写超时时间、所有类型的超时时间、时间格式
//        pipeline?.addLast(IdleStateHandler(5, 0, 0, TimeUnit.SECONDS))
//        pipeline?.addLast(
//                ObjectDecoder(1024 * 1024, ClassResolvers
//                        .weakCachingConcurrentResolver(this.javaClass
//                                .classLoader)))

        //                        pipeline.addLast("http-codec",new HttpServerCodec());/*HTTP 服务的解码器*/
        //                        pipeline.addLast(new HttpObjectAggregator(2048));/*HTTP 消息的合并处理*/
        //                        pipeline.addLast(new StringDecoder());
        //                        pipeline.addLast(new StringEncoder());

        //                        pipeline.addLast("out1", new OutBoundHandler());
        //                        pipeline.addLast("in1", new InBoundHandler());
        //业务逻辑实现类
    }

}