@Test
  public void listStatusTest() throws Exception {
    AlluxioURI file = new AlluxioURI("/file");
    List<URIStatus> infos = Lists.newArrayList(new URIStatus(new FileInfo()));
    Mockito.when(mFileSystemMasterClient.listStatus(file)).thenReturn(infos);
    ListStatusOptions listStatusOptions = ListStatusOptions.defaults();
    Assert.assertSame(infos, mFileSystem.listStatus(file, listStatusOptions));
    Mockito.verify(mFileSystemMasterClient).listStatus(file);
  }