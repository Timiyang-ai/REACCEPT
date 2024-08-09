@Test
  public void renameTest() throws Exception {
    TachyonURI src = new TachyonURI("/file");
    TachyonURI dst = new TachyonURI("/file2");
    RenameOptions renameOptions = RenameOptions.defaults();
    Mockito.doNothing().when(mFileSystemMasterClient).rename(src, dst);
    mFileSystem.rename(src, dst, renameOptions);
    Mockito.verify(mFileSystemMasterClient).rename(src, dst);
  }