package com.boco.noc.agent.communicate;

import java.net.InetSocketAddress;

import org.apache.log4j.Logger;

import com.boco.noc.agent.Config;
import com.boco.noc.agent.util.LogUtils;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.pool.AbstractChannelPoolMap;
import io.netty.channel.pool.ChannelPoolHandler;
import io.netty.channel.pool.ChannelPoolMap;
import io.netty.channel.pool.FixedChannelPool;
import io.netty.channel.pool.SimpleChannelPool;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyChannelPool {
	final EventLoopGroup group = new NioEventLoopGroup();
	final Bootstrap strap = new Bootstrap();

	InetSocketAddress default_addr = new InetSocketAddress(Config.REMOTE_HOST, Config.REMOTE_PORT);
	ChannelPoolMap<InetSocketAddress, SimpleChannelPool> poolMap;
	private static final int MAX_CHANNEL_COUNT = 4;
	private Logger logger = Logger.getLogger(NettyChannelPool.class);

	public NettyChannelPool build() {
		strap.group(group).channel(NioSocketChannel.class)
				.option(ChannelOption.TCP_NODELAY, true)//解决tcp粘包.
				.option(ChannelOption.SO_KEEPALIVE, true);

		poolMap = new AbstractChannelPoolMap<InetSocketAddress, SimpleChannelPool>() {
			@Override
			protected SimpleChannelPool newPool(InetSocketAddress key) {
				return new FixedChannelPool(strap.remoteAddress(key), new ChannelPoolHandler() {

					@Override
					public void channelReleased(Channel ch) throws Exception {
						LogUtils.logDebug(logger, "Release channel:" + ch);
					}

					@Override
					public void channelAcquired(Channel ch) throws Exception {
						LogUtils.logDebug(logger, "Acquired channel:" + ch);
					}

					@Override
					public void channelCreated(Channel ch) throws Exception {
						LogUtils.logDebug(logger, "Created channel:" + ch);
						SocketChannel channel = (SocketChannel) ch;
						channel.config().setKeepAlive(true);
					}

				}, MAX_CHANNEL_COUNT);
			}
		};
		return this;
	}

	public Channel get() {
		return null;
	}

	public void retrieve(Channel c) {

	}
}