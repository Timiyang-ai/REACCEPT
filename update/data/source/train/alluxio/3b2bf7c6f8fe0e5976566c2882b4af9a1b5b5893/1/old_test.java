@Test
  public void startTest() throws Exception {
    String hostname = NetworkAddressUtils.getLocalHostName(new Configuration());
    Mockito.verify(mRMClient).registerApplicationMaster(hostname, 0, "");
  }