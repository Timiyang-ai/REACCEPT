  @Test
  public void resolveHostName() throws UnknownHostException {
    assertEquals(NetworkAddressUtils.resolveHostName(""), null);
    assertEquals(NetworkAddressUtils.resolveHostName(null), null);
    assertEquals(NetworkAddressUtils.resolveHostName("localhost"), "localhost");
  }