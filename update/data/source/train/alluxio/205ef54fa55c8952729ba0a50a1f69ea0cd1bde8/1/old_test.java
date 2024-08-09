@Test
  public void testGetBindAddress() throws Exception {
    for (ServiceType service : ServiceType.values()) {
      getBindAddress(service);
    }
  }