  @Test
  public void temporaryFileName() {
    assertEquals(PathUtils.temporaryFileName(1, "/"),
        PathUtils.temporaryFileName(1, "/"));
    assertNotEquals(PathUtils.temporaryFileName(1, "/"),
        PathUtils.temporaryFileName(2, "/"));
    assertNotEquals(PathUtils.temporaryFileName(1, "/"),
        PathUtils.temporaryFileName(1, "/a"));
  }