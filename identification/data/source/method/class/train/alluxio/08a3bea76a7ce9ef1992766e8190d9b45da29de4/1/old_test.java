  @Test
  public void isRootTests() {
    assertFalse(new AlluxioURI(".").isRoot());
    assertTrue(new AlluxioURI("/").isRoot());
    assertTrue(new AlluxioURI("file:/").isRoot());
    assertTrue(new AlluxioURI("alluxio://localhost:19998").isRoot());
    assertTrue(new AlluxioURI("alluxio://localhost:19998/").isRoot());
    assertTrue(new AlluxioURI("hdfs://localhost:19998").isRoot());
    assertTrue(new AlluxioURI("hdfs://localhost:19998/").isRoot());
    assertTrue(new AlluxioURI("file://localhost/").isRoot());
    assertFalse(new AlluxioURI("file://localhost/a/b").isRoot());
    assertFalse(new AlluxioURI("a/b").isRoot());
  }