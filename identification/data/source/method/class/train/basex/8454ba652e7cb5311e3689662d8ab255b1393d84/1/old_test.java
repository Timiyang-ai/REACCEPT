@Test
  public void testMkdir() {
    // mkdir should only accept directories (worng mode).
    assertEquals("mkdir -1", -1, dbfs.mkdir("/a/x", 0100644));
    assertEquals("mkdir 0", 0, dbfs.mkdir("/a", 0040755));
    assertEquals("mkdir 0", 0, dbfs.mkdir("/a/b", 0040755));
    // non-existing parent directories.
    assertEquals("mkdir -1", -1, dbfs.mkdir("/a/b/c/d/e", 0040755));
    assertEquals("mkdir 0", 0, dbfs.mkdir("/a/b/c", 0040755));
    assertEquals("mkdir 0", 0, dbfs.mkdir("/a/b/d", 0040755));
    assertEquals("mkdir 0", 0, dbfs.mkdir("/a/c", 0040755));
    final String r3 = "<deepfuse mountpoint=\"unknown\">"
        + "<dir name=\"a\"><dir name=\"b\"><dir name=\"c\"/>"
        + "<dir name=\"d\"/></dir><dir name=\"c\"/></dir></deepfuse>";
    assertEquals("mkdir r3", r3, query("/").trim());
    // already exists (is getattr's task)
  }