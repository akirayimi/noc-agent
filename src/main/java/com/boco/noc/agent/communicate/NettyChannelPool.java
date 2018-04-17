package com.boco.noc.agent.communicate;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;

import com.boco.noc.agent.Config;
import com.boco.noc.agent.util.LogUtils;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class NettyChannelPool {
	private String ipAddress = Config.REMOTE_HOST;
	private int port = Config.REMOTE_PORT;
	private static final int MAX_CHANNEL_COUNT = 4;
	private Logger logger = Logger.getLogger(NettyChannelPool.class);
	private BlockingQueue<Channel> channels = new ArrayBlockingQueue<Channel>(MAX_CHANNEL_COUNT);

	public NettyChannelPool() {
		initPool();
	}

	private void initPool() {
		for (int i = 0; i < MAX_CHANNEL_COUNT; i++) {
			try {
				channels.offer(connectToServer());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void retrieve(Channel c) {
		channels.offer(c);
	}

	public Channel get() {
		Channel channel = null;
		try {
			channel = channels.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return channel;
	}

	private Channel connectToServer() throws InterruptedException {
		EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).option(ChannelOption.SO_KEEPALIVE, Boolean.TRUE)
				.option(ChannelOption.TCP_NODELAY, Boolean.TRUE).handler(new LoggingHandler(LogLevel.INFO))
				.handler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ChannelPipeline pipeline = ch.pipeline();
					}
				});

		ChannelFuture channelFuture = bootstrap.connect(ipAddress, port);
		Channel channel = channelFuture.sync().channel();
		LogUtils.logDebug(logger, "create channel with remote host: " + ipAddress + ":" + port + " success.");
		return channel;
	}

}