@Test
  public void openFile() throws Exception {
    AlluxioURI file = new AlluxioURI("/file");
    URIStatus status = new URIStatus(new FileInfo());
    GetStatusOptions getStatusOptions = GetStatusOptions.defaults();
    when(mFileSystemMasterClient.getStatus(file, getStatusOptions)).thenReturn(status);
    OpenFileOptions openOptions = OpenFileOptions.defaults();
    mFileSystem.openFile(file, openOptions);
    verify(mFileSystemMasterClient).getStatus(file, getStatusOptions);

    verifyFilesystemContextAcquiredAndReleased();
  }