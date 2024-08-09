@Test
  public void createFile() throws Exception {
    URIStatus status = new URIStatus(new FileInfo());
    AlluxioURI file = new AlluxioURI("/file");
    when(mFileSystemMasterClient.createFile(any(AlluxioURI.class), any(CreateFilePOptions.class)))
        .thenReturn(status);
    FileOutStream out = mFileSystem.createFile(file, CreateFilePOptions.getDefaultInstance());
    verify(mFileSystemMasterClient).createFile(file, FileSystemOptions.createFileDefaults(mConf)
            .toBuilder().mergeFrom(CreateFilePOptions.getDefaultInstance()).build());
    assertEquals(out.mUri, file);

    verifyFilesystemContextAcquiredAndReleased();
  }