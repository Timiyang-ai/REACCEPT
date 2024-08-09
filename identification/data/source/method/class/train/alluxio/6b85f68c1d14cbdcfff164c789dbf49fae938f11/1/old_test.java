@Test
  public void getStatus() throws Exception {
    AlluxioURI file = new AlluxioURI("/file");
    URIStatus status = new URIStatus(new FileInfo());
    Mockito.when(mFileSystemMasterClient.getStatus(file)).thenReturn(status);
    GetStatusOptions getStatusOptions = GetStatusOptions.defaults();
    Assert.assertSame(status, mFileSystem.getStatus(file, getStatusOptions));
    Mockito.verify(mFileSystemMasterClient).getStatus(file);
  }