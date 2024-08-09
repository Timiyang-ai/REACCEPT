@Test
  public void createDirectoryTest() throws Exception {
    AlluxioURI dir = new AlluxioURI("/dir");
    CreateDirectoryOptions createDirectoryOptions = CreateDirectoryOptions.defaults();
    Mockito.doNothing().when(mFileSystemMasterClient).createDirectory(dir, createDirectoryOptions);
    mFileSystem.createDirectory(dir, createDirectoryOptions);
    Mockito.verify(mFileSystemMasterClient).createDirectory(dir, createDirectoryOptions);
  }