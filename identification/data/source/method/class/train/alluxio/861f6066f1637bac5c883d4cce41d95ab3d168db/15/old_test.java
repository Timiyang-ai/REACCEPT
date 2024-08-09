@Test
  public void createDirectoryTest() throws Exception {
    TachyonURI dir = new TachyonURI("/dir");
    CreateDirectoryOptions createDirectoryOptions = CreateDirectoryOptions.defaults();
    Mockito.doNothing().when(mFileSystemMasterClient).createDirectory(dir, createDirectoryOptions);
    mFileSystem.createDirectory(dir, createDirectoryOptions);
    Mockito.verify(mFileSystemMasterClient).createDirectory(dir, createDirectoryOptions);
  }