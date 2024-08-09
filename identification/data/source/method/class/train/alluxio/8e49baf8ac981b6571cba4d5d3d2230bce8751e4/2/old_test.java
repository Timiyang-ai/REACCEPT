  @Test
  public void rmdir() throws Exception {
    AlluxioURI expectedPath = BASE_EXPECTED_URI.join("/foo/bar");
    doNothing().when(mFileSystem).delete(expectedPath);
    mFuseFs.rmdir("/foo/bar");
    verify(mFileSystem).delete(expectedPath);
  }