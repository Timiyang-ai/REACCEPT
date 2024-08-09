@Test
  public void testGetDriverConfigBundle() {
    when(resourceRequests.readDriver()).thenReturn(driverBean(driver()));

    client.getDriverConfigBundle();
    client.getDriverConfig();

    verify(resourceRequests, times(1)).readDriver();
  }