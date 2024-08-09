@Test
  public void testGetDriverConfig() {
    when(resourceRequests.readDriver()).thenReturn(driverBean(driver()));

    client.getDriverConfig();
    client.getDriverConfigBundle();

    verify(resourceRequests, times(1)).readDriver();
  }