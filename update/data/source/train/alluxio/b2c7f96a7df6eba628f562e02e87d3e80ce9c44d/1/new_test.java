@Test
  public void getFqdnHostTest() throws UnknownHostException {
    Assert.assertEquals(NetworkAddressUtils.getFqdnHost(new InetSocketAddress("localhost", 0)),
        "localhost");
    Assert.assertEquals(NetworkAddressUtils.getFqdnHost(new NetAddress("localhost", 0, 0, 0)),
        "localhost");
  }