@Test
  public void createFile() throws Exception {
    doNothing().when(mFileSystemMasterClient)
        .createFile(any(AlluxioURI.class), any(CreateFileOptions.class));
    URIStatus status = new URIStatus(new FileInfo());
    AlluxioURI file = new AlluxioURI("/file");
    GetStatusOptions getStatusOptions = GetStatusOptions.defaults().setLoadMetadataType(
        LoadMetadataType.Never);
    when(mFileSystemMasterClient.getStatus(file, getStatusOptions)).thenReturn(status);
    CreateFileOptions options = CreateFileOptions.defaults();
    FileOutStream out = mFileSystem.createFile(file, options);
    verify(mFileSystemMasterClient).createFile(file, options);
    assertEquals(out.mUri, file);

    verifyFilesystemContextAcquiredAndReleased();
  }