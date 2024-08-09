@Test
  public void openFileTest() throws Exception {
    AlluxioURI file = new AlluxioURI("/file");
    URIStatus status = new URIStatus(new FileInfo());
    Mockito.when(mFileSystemMasterClient.getStatus(file)).thenReturn(status);
    OpenFileOptions openOptions = OpenFileOptions.defaults();
    mFileSystem.openFile(file, openOptions);
    Mockito.verify(mFileSystemMasterClient).getStatus(file);
  }