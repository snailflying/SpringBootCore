package com.theone.web.netty

import com.google.protobuf.MessageLite
import com.theone.web.proto.OneTestProto
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandler.Sharable
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToByteEncoder

/**
 * 参考ProtobufVarint32LengthFieldPrepender 和 ProtobufEncoder
 */
@Sharable
class ProtobufEncoder : MessageToByteEncoder<MessageLite>() {

    @Throws(Exception::class)
    override fun encode(
            ctx: ChannelHandlerContext, msg: MessageLite, out: ByteBuf) {


        val body = msg.toByteArray()
        val header = encodeHeader(msg, body.size)

        out.writeBytes(header)
        out.writeBytes(body)

    }

    private fun encodeHeader(msg: MessageLite, bodyLength: Int): ByteArray {
        var messageType: Byte = 0x0f

        if (msg is OneTestProto.OneTest) {
            messageType = 0x00
        } else{
            messageType = 0x00
        }
        //        else if (msg instanceof Test.ProtoTest) {
        //            messageType = 0x01;
        //        }

        val header = ByteArray(4)
        header[0] = (bodyLength and 0xff).toByte()
        header[1] = (bodyLength shr 8 and 0xff).toByte()
        header[2] = messageType
        header[3] = 0 // 保留字段

        return header

    }
}