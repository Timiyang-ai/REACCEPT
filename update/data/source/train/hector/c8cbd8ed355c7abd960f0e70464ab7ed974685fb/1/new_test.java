@Test
  public void testDescribeThriftVersion() throws TTransportException, TException, UnknownHostException {
    CassandraCluster cassandraCluster = new CassandraClusterFactory(cassandraClient).create();
    assertEquals("2.1.0",cassandraCluster.describeThriftVersion());
  }