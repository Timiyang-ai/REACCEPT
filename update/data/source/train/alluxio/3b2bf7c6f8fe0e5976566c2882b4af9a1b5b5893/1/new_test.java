@Test
  public void startTest() throws Exception {
    String hostname = NetworkAddressUtils.getLocalHostName();
    Mockito.verify(mRMClient).registerApplicationMaster(hostname, 0, "");
  }