@Test
  public void createFileTest() throws Exception {
    Mockito.doNothing().when(mFileSystemMasterClient)
        .createFile(Mockito.any(TachyonURI.class), Mockito.any(CreateFileOptions.class));
    TachyonURI file = new TachyonURI("/file");
    CreateFileOptions options = CreateFileOptions.defaults();
    FileOutStream out = mFileSystem.createFile(file, options);
    Mockito.verify(mFileSystemMasterClient).createFile(file, options);
    Assert.assertEquals(out.mUri, file);
  }