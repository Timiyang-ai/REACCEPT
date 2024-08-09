  @Test
  public void match() {
    assertEquals(FileSystemShellUtils.match("/a/b/c", "/a/*"), true);
    assertEquals(FileSystemShellUtils.match("/a/b/c", "/a/*/"), true);
    assertEquals(FileSystemShellUtils.match("/a/b/c", "/a/*/c"), true);
    assertEquals(FileSystemShellUtils.match("/a/b/c", "/a/*/*"), true);
    assertEquals(FileSystemShellUtils.match("/a/b/c", "/a/*/*/"), true);
    assertEquals(FileSystemShellUtils.match("/a/b/c/", "/a/*/*/"), true);
    assertEquals(FileSystemShellUtils.match("/a/b/c/", "/a/*/*"), true);

    assertEquals(FileSystemShellUtils.match("/foo/bar/foobar/", "/foo*/*"), true);
    assertEquals(FileSystemShellUtils.match("/foo/bar/foobar/", "/*/*/foobar"), true);

    assertEquals(FileSystemShellUtils.match("/a/b/c/", "/b/*"), false);
    assertEquals(FileSystemShellUtils.match("/", "/*/*"), false);

    assertEquals(FileSystemShellUtils.match("/a/b/c", "*"), true);
    assertEquals(FileSystemShellUtils.match("/", "/*"), true);
  }