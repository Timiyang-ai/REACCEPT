@Test
  public void renameTest() throws Exception {
    AlluxioURI src = new AlluxioURI("/file");
    AlluxioURI dst = new AlluxioURI("/file2");
    RenameOptions renameOptions = RenameOptions.defaults();
    Mockito.doNothing().when(mFileSystemMasterClient).rename(src, dst);
    mFileSystem.rename(src, dst, renameOptions);
    Mockito.verify(mFileSystemMasterClient).rename(src, dst);
  }