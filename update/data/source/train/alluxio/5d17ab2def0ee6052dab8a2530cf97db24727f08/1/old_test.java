@Test
  public void resolveIpAddress() throws UnknownHostException {
    assertEquals(NetworkAddressUtils.resolveIpAddress(""), null);
    assertEquals(NetworkAddressUtils.resolveIpAddress(null), null);
    assertEquals(NetworkAddressUtils.resolveIpAddress("localhost"), "127.0.0.1");
    assertEquals(NetworkAddressUtils.resolveIpAddress("127.0.0.1"), "127.0.0.1");
  }