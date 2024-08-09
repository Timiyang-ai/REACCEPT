@Test
  public void testDescribeThriftVersion() throws Exception {
    assertEquals("2.1.0",cassandraCluster.describeThriftVersion());
  }