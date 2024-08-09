@Test
  public void testGetConnector() {
    when(requests.readConnector(1L)).thenReturn(connectorBean(connector(1)));
    MConnector connector = client.getConnector(1);
    assertEquals(1, connector.getPersistenceId());

    client.getResourceBundle(1L);

    verify(requests, times(1)).readConnector(1L);
  }