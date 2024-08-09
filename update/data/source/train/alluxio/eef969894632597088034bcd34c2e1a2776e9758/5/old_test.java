@Test
  public void createDirectory() throws Exception {
    AlluxioURI dir = new AlluxioURI("/dir");
    CreateDirectoryPOptions createDirectoryOptions = CreateDirectoryPOptions.getDefaultInstance();
    doNothing().when(mFileSystemMasterClient).createDirectory(dir, createDirectoryOptions);
    mFileSystem.createDirectory(dir, createDirectoryOptions);
    verify(mFileSystemMasterClient).createDirectory(dir, createDirectoryOptions);

    verifyFilesystemContextAcquiredAndReleased();
  }