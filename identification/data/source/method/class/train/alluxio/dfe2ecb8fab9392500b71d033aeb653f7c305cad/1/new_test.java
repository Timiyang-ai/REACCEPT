  @Test
  public void getLeadingPath() {
    assertEquals("/",      new AlluxioURI("/a/b/c/").getLeadingPath(0));
    assertEquals("/a",     new AlluxioURI("/a/b/c/").getLeadingPath(1));
    assertEquals("/a/b",   new AlluxioURI("/a/b/c/").getLeadingPath(2));
    assertEquals("/a/b/c", new AlluxioURI("/a/b/c/").getLeadingPath(3));
    assertEquals(null,     new AlluxioURI("/a/b/c/").getLeadingPath(4));

    assertEquals("/",      new AlluxioURI("/").getLeadingPath(0));

    assertEquals("",       new AlluxioURI("").getLeadingPath(0));
    assertEquals(null,     new AlluxioURI("").getLeadingPath(1));
    assertEquals(".",       new AlluxioURI(".").getLeadingPath(0));
    assertEquals(null,     new AlluxioURI(".").getLeadingPath(1));
    assertEquals("a/b",    new AlluxioURI("a/b/c").getLeadingPath(1));
  }