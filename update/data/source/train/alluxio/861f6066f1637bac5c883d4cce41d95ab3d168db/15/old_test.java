@Test
  public void getStatusTest() throws Exception {
    TachyonURI file = new TachyonURI("/file");
    URIStatus status = new URIStatus(new FileInfo());
    Mockito.when(mFileSystemMasterClient.getStatus(file)).thenReturn(status);
    GetStatusOptions getStatusOptions = GetStatusOptions.defaults();
    Assert.assertSame(status, mFileSystem.getStatus(file, getStatusOptions));
    Mockito.verify(mFileSystemMasterClient).getStatus(file);
  }