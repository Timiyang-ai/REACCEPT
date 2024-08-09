  @Test
  public void toCanonicalPath() {
    assertEquals("", FileUtil.toCanonicalPath(""));
    assertEquals("  ", FileUtil.toCanonicalPath("  "));

    assertEquals("/", FileUtil.toCanonicalPath("/", UNIX_SEPARATOR));
    assertEquals("/", FileUtil.toCanonicalPath("/./", UNIX_SEPARATOR));
    assertEquals("a/b", FileUtil.toCanonicalPath("a/b/", UNIX_SEPARATOR));
    assertEquals("/a/b", FileUtil.toCanonicalPath("/a/////b/", UNIX_SEPARATOR));
    assertEquals("/a/b", FileUtil.toCanonicalPath("/a/././b/", UNIX_SEPARATOR));
    assertEquals("/c", FileUtil.toCanonicalPath("/a/b/../../c", UNIX_SEPARATOR));
    assertEquals("/a", FileUtil.toCanonicalPath("/a/b/..", UNIX_SEPARATOR));
    assertEquals("/a", FileUtil.toCanonicalPath("/a/b/../", UNIX_SEPARATOR));
    assertEquals("/a\\b", FileUtil.toCanonicalPath("/a\\b/", UNIX_SEPARATOR));
    assertEquals("/", FileUtil.toCanonicalPath("/a/../", UNIX_SEPARATOR));
    assertEquals("/", FileUtil.toCanonicalPath("/a/../..", UNIX_SEPARATOR));
    assertEquals("/b", FileUtil.toCanonicalPath("/a/../../b", UNIX_SEPARATOR));
    assertEquals("a...b", FileUtil.toCanonicalPath("a...b", UNIX_SEPARATOR));
    assertEquals("a../b", FileUtil.toCanonicalPath("a../b", UNIX_SEPARATOR));
    assertEquals("a./.b", FileUtil.toCanonicalPath("a./.b", UNIX_SEPARATOR));
    assertEquals("a", FileUtil.toCanonicalPath("a/b/..", UNIX_SEPARATOR));
    assertEquals("a/b", FileUtil.toCanonicalPath("a/b/.", UNIX_SEPARATOR));
    assertEquals("a/b/...", FileUtil.toCanonicalPath("a/b/...", UNIX_SEPARATOR));
    assertEquals("...", FileUtil.toCanonicalPath("...", UNIX_SEPARATOR));
    assertEquals(".local", FileUtil.toCanonicalPath(".local/", UNIX_SEPARATOR));
    assertEquals("file.ext", FileUtil.toCanonicalPath("file.ext", UNIX_SEPARATOR));
    assertEquals("file.", FileUtil.toCanonicalPath("file.", UNIX_SEPARATOR));
    assertEquals("file..", FileUtil.toCanonicalPath("file..", UNIX_SEPARATOR));
    assertEquals("", FileUtil.toCanonicalPath(".", UNIX_SEPARATOR));
    assertEquals("", FileUtil.toCanonicalPath("./", UNIX_SEPARATOR));
    assertEquals("", FileUtil.toCanonicalPath("a/..", UNIX_SEPARATOR));
    assertEquals("b", FileUtil.toCanonicalPath("a/..//b", UNIX_SEPARATOR));
    assertEquals("..", FileUtil.toCanonicalPath("..", UNIX_SEPARATOR));
    assertEquals("..", FileUtil.toCanonicalPath("../", UNIX_SEPARATOR));
    assertEquals("../..", FileUtil.toCanonicalPath("../..", UNIX_SEPARATOR));
    assertEquals("../../..", FileUtil.toCanonicalPath("../../..///./", UNIX_SEPARATOR));
    assertEquals("../a....", FileUtil.toCanonicalPath("../a....//", UNIX_SEPARATOR));
    assertEquals("..", FileUtil.toCanonicalPath("../a..../../", UNIX_SEPARATOR));

    assertEquals("C:/", FileUtil.toCanonicalPath("C:\\", WINDOWS_SEPARATOR));
    assertEquals("a/b", FileUtil.toCanonicalPath("a\\b\\", WINDOWS_SEPARATOR));
    assertEquals("c:/a/b", FileUtil.toCanonicalPath("c:\\a\\\\b\\", WINDOWS_SEPARATOR));
    assertEquals("c:/a/b", FileUtil.toCanonicalPath("c:\\a\\.\\.\\b\\", WINDOWS_SEPARATOR));
    assertEquals("c:/d", FileUtil.toCanonicalPath("c:\\a\\b\\..\\..\\d", WINDOWS_SEPARATOR));
    assertEquals("/a/b", FileUtil.toCanonicalPath("\\a/b\\", WINDOWS_SEPARATOR));
    assertEquals("c:/", FileUtil.toCanonicalPath("c:\\a\\..\\", WINDOWS_SEPARATOR));
    assertEquals("c:/", FileUtil.toCanonicalPath("c:\\a\\..\\..", WINDOWS_SEPARATOR));
    assertEquals("c:/b", FileUtil.toCanonicalPath("c:\\a\\..\\..\\b", WINDOWS_SEPARATOR));
  }