@Test
  public void createDirectory() throws Exception {
    AlluxioURI dir = new AlluxioURI("/dir");
    CreateDirectoryPOptions createDirectoryOptions = CreateDirectoryPOptions.getDefaultInstance();
    doNothing().when(mFileSystemMasterClient).createDirectory(dir,
        FileSystemOptions.createDirectoryDefaults(mConf)
            .toBuilder().mergeFrom(createDirectoryOptions).build());
    mFileSystem.createDirectory(dir, createDirectoryOptions);
    verify(mFileSystemMasterClient).createDirectory(dir,
        FileSystemOptions.createDirectoryDefaults(mConf)
            .toBuilder().mergeFrom(createDirectoryOptions).build());

    verifyFilesystemContextAcquiredAndReleased();
  }