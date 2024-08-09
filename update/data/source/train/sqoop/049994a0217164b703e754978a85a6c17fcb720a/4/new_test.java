@Test
  public void testGetDriverConfig() {
    when(resourceRequests.readDriverConfig()).thenReturn(driverConfigBean(driverConfig()));

    client.getDriverConfig();
    client.getDriverConfigBundle();

    verify(resourceRequests, times(1)).readDriverConfig();
  }