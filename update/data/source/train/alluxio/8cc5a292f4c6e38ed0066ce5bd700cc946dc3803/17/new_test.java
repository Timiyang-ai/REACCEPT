@Test
  public void delete() throws Exception {
    AlluxioURI file = new AlluxioURI("/file");
    DeletePOptions deleteOptions = DeletePOptions.newBuilder().setRecursive(true).build();
    mFileSystem.delete(file, deleteOptions);
    verify(mFileSystemMasterClient).delete(file, deleteOptions);

    verifyFilesystemContextAcquiredAndReleased();
  }