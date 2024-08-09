  @Test
  public void toStringTests() {
    String[] uris =
        new String[] {"/", "/a", "/a/ b", "alluxio://a/b/c d.txt",
            "alluxio://localhost:8080/a/b.txt", "foo", "foo/bar", "/foo/bar#boo", "foo/bar#boo",
            "file:///foo/bar"};
    for (String uri : uris) {
      AlluxioURI turi = new AlluxioURI(uri);
      assertEquals(uri, turi.toString());
    }

    assertEquals(".", new AlluxioURI(".").toString());
    assertEquals("file:///a", new AlluxioURI("file:///a").toString());
    assertEquals("file:///a", new AlluxioURI("file", null, "/a").toString());
  }