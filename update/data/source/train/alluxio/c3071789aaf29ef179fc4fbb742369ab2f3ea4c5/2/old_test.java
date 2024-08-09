@Test
  public void listStatusTest() throws Exception {
    List<FileInfo> infos = Lists.newArrayList(new FileInfo());
    Mockito.when(mFileSystemMasterClient.getFileInfoList(FILE_ID)).thenReturn(infos);
    ListStatusOptions listStatusOptions = ListStatusOptions.defaults();
    Assert.assertSame(infos, mFileSystem.listStatus(new TachyonFile(FILE_ID), listStatusOptions));
    Mockito.verify(mFileSystemMasterClient).getFileInfoList(FILE_ID);
  }