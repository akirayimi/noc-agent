package com.boco.noc.agent.communicate;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

import com.boco.noc.agent.Config;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.pool.ChannelPool;
import io.netty.channel.pool.ChannelPoolMap;
import io.netty.channel.pool.SimpleChannelPool;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;

public class NettyClient {
	private final static InetSocketAddress addr = new InetSocketAddress(Config.REMOTE_HOST, Config.REMOTE_PORT);
	private final static ChannelPoolMap<InetSocketAddress, SimpleChannelPool> poolMap = 
			new NettyChannelPool().build().poolMap;
	

	public static void send(InetSocketAddress addr, final String msg) {
		ChannelPool pool = poolMap.get(addr);
		Future<Channel> f = pool.acquire();
		f.addListener(new FutureListener<Channel>() {
			@Override
			public void operationComplete(Future<Channel> future) throws Exception {
				if (future.isSuccess()) {
					Channel ch = future.getNow();
					ByteBuf buf = Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8);
					ch.writeAndFlush(buf).addListener(new ChannelFutureListener(){
						@Override
				        public void operationComplete(ChannelFuture future) {
				            pool.release(future.channel());
				        }
					});
				}
			}
		});
	}

	public static void send(String msg) {
		send(addr, msg);
	}
}
