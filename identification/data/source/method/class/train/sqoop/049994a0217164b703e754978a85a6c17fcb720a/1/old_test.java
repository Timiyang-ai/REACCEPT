@Test(expected = SqoopException.class)
  public void testNewConnection() {
    when(requests.readConnector(null)).thenReturn(connectorBean(connector(1)));
    client.newConnection("non existing connector");
  }