  @Test
  public void getDepthTests() {
    assertEquals(0, new AlluxioURI("").getDepth());
    assertEquals(0, new AlluxioURI(".").getDepth());
    assertEquals(0, new AlluxioURI("/").getDepth());
    assertEquals(1, new AlluxioURI("/a").getDepth());
    assertEquals(3, new AlluxioURI("/a/b/c.txt").getDepth());
    assertEquals(2, new AlluxioURI("/a/b/").getDepth());
    assertEquals(2, new AlluxioURI("a\\b").getDepth());
    assertEquals(1, new AlluxioURI("C:\\a").getDepth());
    assertEquals(1, new AlluxioURI("C:\\\\a").getDepth());
    assertEquals(0, new AlluxioURI("C:\\\\").getDepth());
    assertEquals(0, new AlluxioURI("alluxio://localhost:19998/").getDepth());
    assertEquals(1, new AlluxioURI("alluxio://localhost:19998/a").getDepth());
    assertEquals(2, new AlluxioURI("alluxio://localhost:19998/a/b.txt").getDepth());
  }