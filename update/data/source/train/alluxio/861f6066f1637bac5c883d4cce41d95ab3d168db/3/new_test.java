@Test
  public void freeTest() throws Exception {
    AlluxioURI file = new AlluxioURI("/file");
    FreeOptions freeOptions = FreeOptions.defaults().setRecursive(true);
    mFileSystem.free(file, freeOptions);
    Mockito.verify(mFileSystemMasterClient).free(file, freeOptions);
  }