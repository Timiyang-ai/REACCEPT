@Test
  public void getStatus() throws Exception {
    AlluxioURI file = new AlluxioURI("/file");
    URIStatus status = new URIStatus(new FileInfo());
    GetStatusPOptions getStatusOptions = GetStatusPOptions.getDefaultInstance();
    when(mFileSystemMasterClient.getStatus(file, getStatusOptions)).thenReturn(status);
    assertSame(status, mFileSystem.getStatus(file, getStatusOptions));
    verify(mFileSystemMasterClient).getStatus(file, getStatusOptions);

    verifyFilesystemContextAcquiredAndReleased();
  }