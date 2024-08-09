private NettyServer getNettyServer(Properties properties)
      throws InstantiationException, IOException {
    if (properties == null) {
      // dud properties. should pick up defaults
      properties = new Properties();
    }
    VerifiableProperties verifiableProperties = new VerifiableProperties(properties);
    NettyConfig nettyConfig = new NettyConfig(verifiableProperties);
    NettyMetrics nettyMetrics = new NettyMetrics(new MetricRegistry());
    RestRequestHandler requestHandler = new MockRestRequestResponseHandler();
    PublicAccessLogger publicAccessLogger = new PublicAccessLogger(new String[]{}, new String[]{});
    VIPHealthCheckService vipHealthCheckService = new VIPHealthCheckService("/ambry-frontend/admin");
    return new NettyServer(nettyConfig, nettyMetrics, requestHandler, publicAccessLogger, vipHealthCheckService);
  }