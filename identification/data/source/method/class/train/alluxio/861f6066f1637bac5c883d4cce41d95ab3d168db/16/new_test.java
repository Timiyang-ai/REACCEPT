@Test
  public void deleteTest() throws Exception {
    AlluxioURI file = new AlluxioURI("/file");
    DeleteOptions deleteOptions = DeleteOptions.defaults().setRecursive(true);
    mFileSystem.delete(file, deleteOptions);
    Mockito.verify(mFileSystemMasterClient).delete(file, deleteOptions);
  }