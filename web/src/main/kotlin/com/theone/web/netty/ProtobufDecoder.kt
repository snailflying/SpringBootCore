package com.theone.web.netty

import com.google.protobuf.MessageLite
import com.theone.web.proto.OneTestProto
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder

/**
 * 参考ProtobufVarint32FrameDecoder 和 ProtobufDecoder
 */

class ProtobufDecoder : ByteToMessageDecoder() {

    @Throws(Exception::class)
    override fun decode(ctx: ChannelHandlerContext, `in`: ByteBuf, out: MutableList<Any>) {
        while (`in`.readableBytes() > 4) { // 如果可读长度小于包头长度，退出。
            `in`.markReaderIndex()

            // 获取包头中的body长度
            val low = `in`.readByte().toInt()
            val high = `in`.readByte().toInt()
            val s0 = (low and 0xff)
            var s1 = (high and 0xff)
            s1 = s1 shl 8
            val length = (s0 or s1)

            // 获取包头中的protobuf类型
            val dataType = `in`.readByte()
            // 获取包头中的保留字段,使readerIndex后移
            `in`.readByte()


            // 如果可读长度小于body长度，恢复读指针，退出。
            if (`in`.readableBytes() < length) {
                `in`.resetReaderIndex()
                return
            }

            // 读取body
            val bodyByteBuf = `in`.readBytes(length.toInt())

            val array: ByteArray
            val offset: Int

            val readableLen = bodyByteBuf.readableBytes()
            if (bodyByteBuf.hasArray()) {
                array = bodyByteBuf.array()
                offset = bodyByteBuf.arrayOffset() + bodyByteBuf.readerIndex()
            } else {
                array = ByteArray(readableLen)
                bodyByteBuf.getBytes(bodyByteBuf.readerIndex(), array, 0, readableLen)
                offset = 0
            }

            //反序列化
            val result = decodeBody(dataType, array, offset, readableLen)
            out.add(result)
        }
    }

    @Throws(Exception::class)
    private fun decodeBody(dataType: Byte, array: ByteArray, offset: Int, length: Int): MessageLite {
        return if (dataType.toInt() == 0x00) {
            OneTestProto.OneTest.getDefaultInstance().parserForType.parseFrom(array, offset, length)

        } else {
            OneTestProto.OneTest.getDefaultInstance().parserForType.parseFrom(array, offset, length)

        }
        //        else if (dataType == 0x01) {
        //            return OptionTickOuterClass.OptionTick.getDefaultInstance().
        //                    getParserForType().parseFrom(array, offset, length);
        //        }

        // or throw exception
    }
}