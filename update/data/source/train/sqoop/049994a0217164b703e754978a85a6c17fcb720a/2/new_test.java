@Test
  public void testGetConnector() {
    when(resourceRequests.readConnector(1L)).thenReturn(connectorBean(connector(1)));
    MConnector connector = client.getConnector(1);
    assertEquals(1, connector.getPersistenceId());

    client.getConnectorConfigResourceBundle(1L);

    verify(resourceRequests, times(1)).readConnector(1L);
  }