@Test
  public void testGetDriverConfigBundle() {
    when(resourceRequests.readDriverConfig()).thenReturn(driverConfigBean(driverConfig()));

    client.getDriverConfigBundle();
    client.getDriverConfig();

    verify(resourceRequests, times(1)).readDriverConfig();
  }