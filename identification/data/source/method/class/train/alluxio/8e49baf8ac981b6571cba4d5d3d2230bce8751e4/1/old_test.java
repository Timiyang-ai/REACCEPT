  @Test
  public void unlink() throws Exception {
    AlluxioURI expectedPath = BASE_EXPECTED_URI.join("/foo/bar");
    doNothing().when(mFileSystem).delete(expectedPath);
    mFuseFs.unlink("/foo/bar");
    verify(mFileSystem).delete(expectedPath);
  }