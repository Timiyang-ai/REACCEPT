@Test
  public void openFile() throws Exception {
    AlluxioURI file = new AlluxioURI("/file");
    URIStatus status = new URIStatus(new FileInfo());
    GetStatusPOptions getStatusOptions = GetStatusPOptions.getDefaultInstance();
    when(mFileSystemMasterClient.getStatus(file, FileSystemOptions.getStatusDefaults(mConf)
            .toBuilder().mergeFrom(getStatusOptions).build())).thenReturn(status);
    mFileSystem.openFile(file, OpenFilePOptions.getDefaultInstance());
    verify(mFileSystemMasterClient).getStatus(file,
        FileSystemOptions.getStatusDefaults(mConf).toBuilder().mergeFrom(getStatusOptions).build());

    verifyFilesystemContextAcquiredAndReleased();
  }