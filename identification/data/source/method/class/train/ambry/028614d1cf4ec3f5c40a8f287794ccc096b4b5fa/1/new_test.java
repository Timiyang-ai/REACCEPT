@Test
  public void getNettyServerTest() throws Exception {
    Properties properties = new Properties();
    doGetNettyServerTest(properties, SSL_FACTORY);
    doGetNettyServerTest(properties, null);
    properties.setProperty("netty.server.enable.ssl", "true");
    doGetNettyServerTest(properties, SSL_FACTORY);
  }