private NettyServer getNettyServer(Properties properties)
      throws InstantiationException, IOException {
    if (properties == null) {
      // dud properties. should pick up defaults
      properties = new Properties();
    }
    VerifiableProperties verifiableProperties = new VerifiableProperties(properties);
    NettyConfig nettyConfig = new NettyConfig(verifiableProperties);
    NettyMetrics nettyMetrics = new NettyMetrics(new MetricRegistry());
    RequestResponseHandlerController requestResponseHandlerController = getRequestHandlerController(properties);
    return new NettyServer(nettyConfig, nettyMetrics, requestResponseHandlerController);
  }