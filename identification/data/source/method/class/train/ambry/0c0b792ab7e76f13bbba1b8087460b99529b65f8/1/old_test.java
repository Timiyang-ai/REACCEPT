@Test
  public void getNettyServerTest()
      throws InstantiationException, IOException {
    // dud properties. server should pick up defaults
    Properties properties = new Properties();
    VerifiableProperties verifiableProperties = new VerifiableProperties(properties);
    RequestResponseHandlerController requestResponseHandlerController = getRequestHandlerController();

    NioServerFactory nioServerFactory =
        new NettyServerFactory(verifiableProperties, new MetricRegistry(), requestResponseHandlerController);
    NioServer nioServer = nioServerFactory.getNioServer();
    assertNotNull("No NioServer returned", nioServer);
    assertEquals("Did not receive a NettyServer instance", NettyServer.class.getCanonicalName(),
        nioServer.getClass().getCanonicalName());
  }