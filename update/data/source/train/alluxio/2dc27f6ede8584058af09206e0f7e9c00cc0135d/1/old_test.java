@Test
  public void getSchemeTests() {
    Assert.assertEquals(null, new AlluxioURI("/").getScheme());
    Assert.assertEquals("file", new AlluxioURI("file:/").getScheme());
    Assert.assertEquals("file", new AlluxioURI("file://localhost/").getScheme());
    Assert.assertEquals("alluxio-ft", new AlluxioURI("alluxio-ft://localhost/").getScheme());
    Assert.assertEquals("s3", new AlluxioURI("s3://localhost/").getScheme());
    Assert.assertEquals("alluxio", new AlluxioURI("alluxio://localhost/").getScheme());
    Assert.assertEquals("hdfs", new AlluxioURI("hdfs://localhost/").getScheme());
    Assert.assertEquals("glusterfs", new AlluxioURI("glusterfs://localhost/").getScheme());
  }