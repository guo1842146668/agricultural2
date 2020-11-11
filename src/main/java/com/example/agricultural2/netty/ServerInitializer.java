package com.example.agricultural2.netty;

import com.example.agricultural2.service.WorkService;
import com.example.agricultural2.service.impl.MachineryServiceImpl;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import javax.annotation.Resource;

/**
 * 服务
 * @author JHS
 */
public class ServerInitializer extends ChannelInitializer<SocketChannel> {

    private MachineryServiceImpl machineryService;
    private WorkService workService;

    public ServerInitializer(MachineryServiceImpl machineryService,WorkService workService){
        this.machineryService = machineryService;
        this.workService = workService;
    }
    @Override
    protected void initChannel(SocketChannel ch){
        ChannelPipeline pipeline = ch.pipeline();
        ByteBuf delimiter = Unpooled.copiedBuffer(" ".getBytes());
        // 以("\n")为结尾分割的 解码器
        //pipeline.addLast("framer", new DelimiterBasedFrameDecoder(4096,delimiter));

        new HttpServerCodec();
        // 字符串解码 和 编码
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder",new StringEncoder());
        //pipeline.addLast( new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 4, 4, -8, 0));
        // 自己的逻辑Handler
        pipeline.addLast("handler", new ServerHandler(machineryService,workService));
    }


}
