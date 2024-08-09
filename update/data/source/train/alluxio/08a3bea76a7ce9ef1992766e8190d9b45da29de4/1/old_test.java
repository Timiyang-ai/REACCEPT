@Test
  public void getHostTests() {
    assertEquals(null, new AlluxioURI(".").getHost());
    assertEquals(null, new AlluxioURI("/").getHost());
    assertEquals(null, new AlluxioURI("file", "", "/a/b.txt").getHost());
    assertEquals(null, new AlluxioURI("file", null, "/a/b.txt").getHost());
    assertEquals("localhost", new AlluxioURI("s3", "localhost", "/a/b.txt").getHost());
    assertEquals("localhost", new AlluxioURI("s3", "localhost:8080", "/a/b.txt").getHost());
    assertEquals("127.0.0.1", new AlluxioURI("s3", "127.0.0.1", "/a/b.txt").getHost());
    assertEquals("127.0.0.1", new AlluxioURI("s3", "127.0.0.1:8080", "/a/b.txt").getHost());
  }