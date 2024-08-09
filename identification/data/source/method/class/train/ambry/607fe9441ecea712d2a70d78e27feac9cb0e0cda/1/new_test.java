@Test
  public void getNettyServerTest() {
    // dud properties. server should pick up defaults
    Properties properties = new Properties();
    VerifiableProperties verifiableProperties = new VerifiableProperties(properties);
    RestRequestHandler restRequestHandler = new MockRestRequestResponseHandler();
    PublicAccessLogger publicAccessLogger = new PublicAccessLogger(new String[]{}, new String[]{});
    VIPHealthCheckService vipHealthCheckService = new VIPHealthCheckService("/ambry-frontend/admin");

    NettyServerFactory nettyServerFactory =
        new NettyServerFactory(verifiableProperties, new MetricRegistry(), restRequestHandler, publicAccessLogger,
            vipHealthCheckService);
    NioServer nioServer = nettyServerFactory.getNioServer();
    assertNotNull("No NioServer returned", nioServer);
    assertEquals("Did not receive a NettyServer instance", NettyServer.class.getCanonicalName(),
        nioServer.getClass().getCanonicalName());
  }