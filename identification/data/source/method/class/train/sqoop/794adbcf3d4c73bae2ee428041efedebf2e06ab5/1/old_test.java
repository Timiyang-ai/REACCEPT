@Test
  public void testGetConnectors() {
    MConnector connector;

    when(resourceRequests.readConnector(null)).thenReturn(connectorBean(connector(1), connector(2)));
    Collection<MConnector> connectors = client.getConnectors();
    assertEquals(2, connectors.size());

    client.getConnectorConfigBundle(1);
    connector = client.getConnector(1);
    assertEquals(1, connector.getPersistenceId());

    connector = client.getConnector(2);
    client.getConnectorConfigBundle(2);
    assertEquals(2, connector.getPersistenceId());

    connectors = client.getConnectors();
    assertEquals(2, connectors.size());

    connector = client.getConnector("A1");
    assertEquals(1, connector.getPersistenceId());
    assertEquals("A1", connector.getUniqueName());

    connector = client.getConnector("A2");
    assertEquals(2, connector.getPersistenceId());
    assertEquals("A2", connector.getUniqueName());

    connector = client.getConnector("A3");
    assertNull(connector);

    verify(resourceRequests, times(1)).readConnector(null);
    verifyNoMoreInteractions(resourceRequests);
  }