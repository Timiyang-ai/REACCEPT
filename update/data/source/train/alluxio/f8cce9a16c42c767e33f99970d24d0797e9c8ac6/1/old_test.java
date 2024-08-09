@Test
  public void joinTests() {
    assertEquals(new AlluxioURI("/a"), new AlluxioURI("/").join("a"));
    assertEquals(new AlluxioURI("/a"), new AlluxioURI("/").join(new AlluxioURI("a")));
    assertEquals(new AlluxioURI("/a/b"), new AlluxioURI("/a").join(new AlluxioURI("b")));
    assertEquals(new AlluxioURI("a/b"), new AlluxioURI("a").join(new AlluxioURI("b")));
    assertEquals(new AlluxioURI("/a/c"),
        new AlluxioURI("/a").join(new AlluxioURI("b/../c")));
    assertEquals(new AlluxioURI("a/b.txt"),
        new AlluxioURI("a").join(new AlluxioURI("/b.txt")));
    assertEquals(new AlluxioURI("a/b.txt"),
        new AlluxioURI("a").join(new AlluxioURI("/c/../b.txt")));
    assertEquals(new AlluxioURI("alluxio:/a/b.txt"),
        new AlluxioURI("alluxio:/a").join("/b.txt"));
    assertEquals(new AlluxioURI("alluxio:/a/b.txt"),
        new AlluxioURI("alluxio:/a/c.txt").join(new AlluxioURI("/../b.txt")));
    assertEquals(new AlluxioURI("C:\\\\a\\b"),
        new AlluxioURI("C:\\\\a").join(new AlluxioURI("\\b")));
    assertEquals(new AlluxioURI("/a/b"), new AlluxioURI("/a").joinUnsafe("///b///"));

    final String pathWithSpecialChar = "����,��b����$o����[| =B����";
    assertEquals(new AlluxioURI("/" + pathWithSpecialChar),
            new AlluxioURI("/").join(pathWithSpecialChar));

    final String pathWithSpecialCharAndColon = "����,��b����$o����[| =B��:��";
    assertEquals(new AlluxioURI("/" + pathWithSpecialCharAndColon),
        new AlluxioURI("/").join(pathWithSpecialCharAndColon));
  }