  @Test
  public void isAncestor() throws Exception {
    assertTrue(FileUtil.isAncestor("/", "/a/", true));
    assertTrue(FileUtil.isAncestor("/a/b/c", "/a/b/c/d/e/f", true));
    assertTrue(FileUtil.isAncestor("/a/b/c/", "/a/b/c/d/e/f", true));
    assertFalse(FileUtil.isAncestor("/a/b/c/1", "/a/b/c/2", true));
    assertFalse(FileUtil.isAncestor("/a/b/c/1", "/a/b/c/2", false));
    assertTrue(FileUtil.isAncestor("/a/b/c/", "/a/b/c", false));
    assertTrue(FileUtil.isAncestor("/a///b/c", "/a/b/c/", false));
    assertFalse(FileUtil.isAncestor("/a/b/c/", "/a/./b/c", true));
    assertFalse(FileUtil.isAncestor("/a/b/c", "/a/b/c/", true));
    assertFalse(FileUtil.isAncestor("/a/b/c", "/a/b/cde", true));
    assertFalse(FileUtil.isAncestor("/a/b/c", "/a/b/cde", false));

    assertEquals(SystemInfo.isWindows, FileUtil.isAncestor("c:\\", "C:/a/b/c", true));
    assertEquals(!SystemInfo.isFileSystemCaseSensitive, FileUtil.isAncestor("/a/b/c", "/a/B/c/d", true));
  }