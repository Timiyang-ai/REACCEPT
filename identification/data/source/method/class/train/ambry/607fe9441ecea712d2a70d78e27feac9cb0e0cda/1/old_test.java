@Test
  public void getNettyServerTest() {
    // dud properties. server should pick up defaults
    Properties properties = new Properties();
    VerifiableProperties verifiableProperties = new VerifiableProperties(properties);
    RestRequestHandler restRequestHandler = new MockRestRequestResponseHandler();

    NettyServerFactory nettyServerFactory =
        new NettyServerFactory(verifiableProperties, new MetricRegistry(), restRequestHandler);
    NioServer nioServer = nettyServerFactory.getNioServer();
    assertNotNull("No NioServer returned", nioServer);
    assertEquals("Did not receive a NettyServer instance", NettyServer.class.getCanonicalName(),
        nioServer.getClass().getCanonicalName());
  }