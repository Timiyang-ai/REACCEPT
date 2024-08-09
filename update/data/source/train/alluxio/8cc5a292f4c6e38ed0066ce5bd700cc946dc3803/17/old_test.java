@Test
  public void delete() throws Exception {
    AlluxioURI file = new AlluxioURI("/file");
    DeleteOptions deleteOptions = DeleteOptions.defaults().setRecursive(true);
    mFileSystem.delete(file, deleteOptions);
    verify(mFileSystemMasterClient).delete(file, deleteOptions);

    verifyFilesystemContextAcquiredAndReleased();
  }