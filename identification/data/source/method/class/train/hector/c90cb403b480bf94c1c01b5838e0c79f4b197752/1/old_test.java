@Test
  public void testDescribeThriftVersion() throws TTransportException, TException, UnknownHostException {
    assertEquals("2.1.0",cassandraCluster.describeThriftVersion());
  }