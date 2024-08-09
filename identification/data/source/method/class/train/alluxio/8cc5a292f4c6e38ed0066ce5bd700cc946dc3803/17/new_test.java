@Test
  public void openFile() throws Exception {
    AlluxioURI file = new AlluxioURI("/file");
    URIStatus status = new URIStatus(new FileInfo());
    GetStatusPOptions getStatusOptions = GetStatusPOptions.getDefaultInstance();
    when(mFileSystemMasterClient.getStatus(file, getStatusOptions)).thenReturn(status);
    mFileSystem.openFile(file, OpenFilePOptions.getDefaultInstance());
    verify(mFileSystemMasterClient).getStatus(file, getStatusOptions);

    verifyFilesystemContextAcquiredAndReleased();
  }