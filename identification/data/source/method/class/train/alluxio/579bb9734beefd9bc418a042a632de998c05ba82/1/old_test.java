  @Test
  public void getSchemeTests() {
    assertEquals(null, new AlluxioURI(".").getScheme());
    assertEquals(null, new AlluxioURI("/").getScheme());
    assertEquals("file", new AlluxioURI("file:/").getScheme());
    assertEquals("file", new AlluxioURI("file://localhost/").getScheme());
    assertEquals("s3", new AlluxioURI("s3://localhost/").getScheme());
    assertEquals("alluxio", new AlluxioURI("alluxio://localhost/").getScheme());
    assertEquals("hdfs", new AlluxioURI("hdfs://localhost/").getScheme());
    assertEquals("glusterfs", new AlluxioURI("glusterfs://localhost/").getScheme());
    assertEquals("scheme:part1", new AlluxioURI("scheme:part1://localhost/").getScheme());
    assertEquals("scheme:part1:part2",
        new AlluxioURI("scheme:part1:part2://localhost/").getScheme());
  }