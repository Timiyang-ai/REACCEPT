  @Test
  public void rename() throws Exception {
    AlluxioURI oldPath = BASE_EXPECTED_URI.join("/old");
    AlluxioURI newPath = BASE_EXPECTED_URI.join("/new");
    doNothing().when(mFileSystem).rename(oldPath, newPath);
    mFuseFs.rename("/old", "/new");
    verify(mFileSystem).rename(oldPath, newPath);
  }