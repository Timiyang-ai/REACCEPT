@Test
  public void freeTest() throws Exception {
    TachyonURI file = new TachyonURI("/file");
    FreeOptions freeOptions = FreeOptions.defaults().setRecursive(true);
    mFileSystem.free(file, freeOptions);
    Mockito.verify(mFileSystemMasterClient).free(file, freeOptions);
  }