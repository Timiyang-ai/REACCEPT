@Test
  public void concatPath() {
    assertEquals("/", PathUtils.concatPath("/"));
    assertEquals("/", PathUtils.concatPath("/", ""));
    assertEquals("/bar", PathUtils.concatPath("/", "bar"));

    assertEquals("foo", PathUtils.concatPath("foo"));
    assertEquals("/foo", PathUtils.concatPath("/foo"));
    assertEquals("/foo", PathUtils.concatPath("/foo", ""));

    // Join base without trailing "/"
    assertEquals("/foo/bar", PathUtils.concatPath("/foo", "bar"));
    assertEquals("/foo/bar", PathUtils.concatPath("/foo", "bar/"));
    assertEquals("/foo/bar", PathUtils.concatPath("/foo", "/bar"));
    assertEquals("/foo/bar", PathUtils.concatPath("/foo", "/bar/"));

    // Join base with trailing "/"
    assertEquals("/foo/bar", PathUtils.concatPath("/foo/", "bar"));
    assertEquals("/foo/bar", PathUtils.concatPath("/foo/", "bar/"));
    assertEquals("/foo/bar", PathUtils.concatPath("/foo/", "/bar"));
    assertEquals("/foo/bar", PathUtils.concatPath("/foo/", "/bar/"));

    // Redundant separator must be trimmed.
    assertEquals("/foo/bar", PathUtils.concatPath("/foo/", "bar//"));

    // Multiple components to join.
    assertEquals("/foo/bar/a/b/c", PathUtils.concatPath("/foo", "bar", "a", "b", "c"));
    assertEquals("/foo/bar/b/c", PathUtils.concatPath("/foo", "bar", "", "b", "c"));

    // Non-string
    assertEquals("/foo/bar/1", PathUtils.concatPath("/foo", "bar", 1));
    assertEquals("/foo/bar/2", PathUtils.concatPath("/foo", "bar", 2L));

    // Header
    assertEquals(Constants.HEADER + "host:port/foo/bar",
        PathUtils.concatPath(Constants.HEADER + "host:port", "/foo", "bar"));
  }