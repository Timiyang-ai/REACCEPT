  @Test
  public void getPermanentFileName() {
    assertEquals("/", PathUtils.getPermanentFileName(PathUtils.temporaryFileName(1, "/")));
    assertEquals("/",
        PathUtils.getPermanentFileName(PathUtils.temporaryFileName(0xFFFFFFFFFFFFFFFFL, "/")));
    assertEquals("/foo.alluxio.0x0123456789ABCDEF.tmp", PathUtils
        .getPermanentFileName(PathUtils.temporaryFileName(14324,
            "/foo.alluxio.0x0123456789ABCDEF.tmp")));
  }