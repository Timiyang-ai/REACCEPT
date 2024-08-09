@Test
  public void testDescribeVersion() throws TTransportException, TException, UnknownHostException {
    CassandraCluster cassandraCluster = new CassandraClusterFactory(cassandraClient).create();
    assertEquals("2.1.0",cassandraCluster.describeVersion());
  }