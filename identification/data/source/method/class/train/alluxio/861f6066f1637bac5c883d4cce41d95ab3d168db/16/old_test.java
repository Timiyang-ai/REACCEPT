@Test
  public void deleteTest() throws Exception {
    TachyonURI file = new TachyonURI("/file");
    DeleteOptions deleteOptions = DeleteOptions.defaults().setRecursive(true);
    mFileSystem.delete(file, deleteOptions);
    Mockito.verify(mFileSystemMasterClient).delete(file, deleteOptions);
  }