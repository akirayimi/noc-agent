package com.boco.noc.agent.communicate;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

public class NettyClient {
	private final static NettyChannelPool nettyChannelPool = new NettyChannelPool();
	private final static ByteBufAllocator allocator = new UnpooledByteBufAllocator(false);

	public static void send(String msg) {
		ByteBuf responseBuf = allocator.buffer(msg.getBytes().length);
		responseBuf.writeBytes(msg.getBytes());
		Channel c = nettyChannelPool.get();
		System.out.println(c);
		ChannelFuture cf = c.writeAndFlush(responseBuf);
		cf.addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				nettyChannelPool.retrieve(c);
			}

		});
	}
}
