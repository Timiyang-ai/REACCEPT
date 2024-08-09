@Test
  public void createFile() throws Exception {
    doNothing().when(mFileSystemMasterClient)
        .createFile(any(AlluxioURI.class), any(CreateFilePOptions.class));
    URIStatus status = new URIStatus(new FileInfo());
    AlluxioURI file = new AlluxioURI("/file");
    GetStatusPOptions getStatusOptions = FileSystemOptions.getStatusDefaults(mConf)
        .toBuilder().setLoadMetadataType(LoadMetadataPType.NEVER).build();
    when(mFileSystemMasterClient.getStatus(file, getStatusOptions)).thenReturn(status);
    FileOutStream out = mFileSystem.createFile(file, CreateFilePOptions.getDefaultInstance());
    verify(mFileSystemMasterClient).createFile(file, FileSystemOptions.createFileDefaults(mConf)
            .toBuilder().mergeFrom(CreateFilePOptions.getDefaultInstance()).build());
    assertEquals(out.mUri, file);

    verifyFilesystemContextAcquiredAndReleased();
  }