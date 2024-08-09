@Test
  public void getStatus() throws Exception {
    AlluxioURI file = new AlluxioURI("/file");
    URIStatus status = new URIStatus(new FileInfo());
    GetStatusOptions getStatusOptions = GetStatusOptions.defaults();
    Mockito.when(mFileSystemMasterClient.getStatus(file, getStatusOptions)).thenReturn(status);
    Assert.assertSame(status, mFileSystem.getStatus(file, getStatusOptions));
    Mockito.verify(mFileSystemMasterClient).getStatus(file, getStatusOptions);
  }