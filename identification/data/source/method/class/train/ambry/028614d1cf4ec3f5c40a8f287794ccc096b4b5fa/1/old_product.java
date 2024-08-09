private NettyServer getNettyServer(Properties properties) throws InstantiationException, IOException {
    if (properties == null) {
      // dud properties. should pick up defaults
      properties = new Properties();
    }
    VerifiableProperties verifiableProperties = new VerifiableProperties(properties);
    final NettyConfig nettyConfig = new NettyConfig(verifiableProperties);
    final NettyMetrics nettyMetrics = new NettyMetrics(new MetricRegistry());
    final RestRequestHandler requestHandler = new MockRestRequestResponseHandler();
    final PublicAccessLogger publicAccessLogger = new PublicAccessLogger(new String[]{}, new String[]{});
    final RestServerState restServerState = new RestServerState("/healthCheck");
    final ConnectionStatsHandler connectionStatsHandler = new ConnectionStatsHandler(nettyMetrics);
    return new NettyServer(nettyConfig, nettyMetrics, new ChannelInitializer<SocketChannel>() {
      @Override
      protected void initChannel(SocketChannel ch) {
        ch.pipeline()
            // connection stats handler to track connection related metrics
            .addLast("ConnectionStatsHandler", connectionStatsHandler)
            // for http encoding/decoding. Note that we get content in 8KB chunks and a change to that number has
            // to go here.
            .addLast("codec", new HttpServerCodec())
            // for health check request handling
            .addLast("healthCheckHandler", new HealthCheckHandler(restServerState, nettyMetrics))
            // for public access logging
            .addLast("publicAccessLogHandler", new PublicAccessLogHandler(publicAccessLogger, nettyMetrics))
            // for detecting connections that have been idle too long - probably because of an error.
            .addLast("idleStateHandler", new IdleStateHandler(0, 0, nettyConfig.nettyServerIdleTimeSeconds))
            // for safe writing of chunks for responses
            .addLast("chunker", new ChunkedWriteHandler())
            // custom processing class that interfaces with a BlobStorageService.
            .addLast("processor", new NettyMessageProcessor(nettyMetrics, nettyConfig, requestHandler));
      }
    });
  }