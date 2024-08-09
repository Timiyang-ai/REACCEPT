@Test
  public void getInfoTest() throws Exception {
    FileInfo info = new FileInfo();
    Mockito.when(mFileSystemMasterClient.getStatus(FILE_ID)).thenReturn(info);
    GetInfoOptions getInfoOptions = GetInfoOptions.defaults();
    Assert.assertSame(info, mFileSystem.getInfo(new TachyonFile(FILE_ID), getInfoOptions));
    Mockito.verify(mFileSystemMasterClient).getStatus(FILE_ID);
  }