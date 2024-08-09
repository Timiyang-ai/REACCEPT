@Test
  public void listStatusTest() throws Exception {
    AlluxioURI file = new AlluxioURI("/file");
    List<URIStatus> infos = new ArrayList<>();
    infos.add(new URIStatus(new FileInfo()));
    ListStatusOptions listStatusOptions = ListStatusOptions.defaults();
    Mockito.when(mFileSystemMasterClient.listStatus(file, listStatusOptions)).thenReturn(infos);
    Assert.assertSame(infos, mFileSystem.listStatus(file, listStatusOptions));
    Mockito.verify(mFileSystemMasterClient).listStatus(file, listStatusOptions);
  }