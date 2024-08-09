@Test
  public void createFileTest() throws Exception {
    Mockito.doNothing().when(mFileSystemMasterClient)
        .createFile(Mockito.any(AlluxioURI.class), Mockito.any(CreateFileOptions.class));
    AlluxioURI file = new AlluxioURI("/file");
    CreateFileOptions options = CreateFileOptions.defaults();
    FileOutStream out = mFileSystem.createFile(file, options);
    Mockito.verify(mFileSystemMasterClient).createFile(file, options);
    Assert.assertEquals(out.mUri, file);
  }