@Test(expected = SqoopException.class)
  public void testCreateLink() {
    when(resourceRequests.readConnector(null)).thenReturn(connectorBean(connector(1)));
    client.createLink("non existing connector");
  }