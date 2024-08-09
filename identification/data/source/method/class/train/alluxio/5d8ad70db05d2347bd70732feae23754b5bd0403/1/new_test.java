  @Test
  public void isTemporaryFileName() {
    assertTrue(PathUtils.isTemporaryFileName(PathUtils.temporaryFileName(0, "/")));
    assertTrue(
        PathUtils.isTemporaryFileName(PathUtils.temporaryFileName(0xFFFFFFFFFFFFFFFFL, "/")));
    assertTrue(PathUtils.isTemporaryFileName("foo.alluxio.0x0123456789ABCDEF.tmp"));
    assertFalse(PathUtils.isTemporaryFileName("foo.alluxio.0x      0123456789.tmp"));
    assertFalse(PathUtils.isTemporaryFileName("foo.alluxio.0x0123456789ABCDEFG.tmp"));
    assertFalse(PathUtils.isTemporaryFileName("foo.alluxio.0x0123456789ABCDE.tmp"));
    assertFalse(PathUtils.isTemporaryFileName("foo.0x0123456789ABCDEFG.tmp"));
    assertFalse(PathUtils.isTemporaryFileName("alluxio.0x0123456789ABCDEFG"));
  }