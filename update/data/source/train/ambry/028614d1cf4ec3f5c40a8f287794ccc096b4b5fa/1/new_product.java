private NettyServer getNettyServer(Properties properties) throws InstantiationException, IOException {
    if (properties == null) {
      // dud properties. should pick up defaults
      properties = new Properties();
    }
    VerifiableProperties verifiableProperties = new VerifiableProperties(properties);
    final NettyConfig nettyConfig = new NettyConfig(verifiableProperties);

    Map<Integer, ChannelInitializer<SocketChannel>> channelInitializers = new HashMap<>();
    channelInitializers.put(nettyConfig.nettyServerPort,
        new NettyServerChannelInitializer(nettyConfig, NETTY_METRICS, CONNECTION_STATS_HANDLER, REQUEST_HANDLER,
            PUBLIC_ACCESS_LOGGER, REST_SERVER_STATE, null));
    channelInitializers.put(nettyConfig.nettyServerSSLPort,
        new NettyServerChannelInitializer(nettyConfig, NETTY_METRICS, CONNECTION_STATS_HANDLER, REQUEST_HANDLER,
            PUBLIC_ACCESS_LOGGER, REST_SERVER_STATE, SSL_FACTORY));
    return new NettyServer(nettyConfig, NETTY_METRICS, channelInitializers);
  }