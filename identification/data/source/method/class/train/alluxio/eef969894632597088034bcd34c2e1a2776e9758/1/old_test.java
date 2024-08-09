@Test
  public void rename() throws Exception {
    AlluxioURI src = new AlluxioURI("/file");
    AlluxioURI dst = new AlluxioURI("/file2");
    RenamePOptions renameOptions = RenamePOptions.getDefaultInstance();
    doNothing().when(mFileSystemMasterClient).rename(src, dst, renameOptions);
    mFileSystem.rename(src, dst, renameOptions);
    verify(mFileSystemMasterClient).rename(src, dst, renameOptions);
  }