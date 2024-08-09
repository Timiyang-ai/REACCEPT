@Test
  public void listStatus() throws Exception {
    AlluxioURI file = new AlluxioURI("/file");
    List<URIStatus> infos = new ArrayList<>();
    infos.add(new URIStatus(new FileInfo()));
    ListStatusPOptions listStatusOptions = ListStatusPOptions.getDefaultInstance();
    when(mFileSystemMasterClient.listStatus(file, FileSystemOptions.listStatusDefaults(mConf)
        .toBuilder().mergeFrom(listStatusOptions).build())).thenReturn(infos);
    assertSame(infos, mFileSystem.listStatus(file, listStatusOptions));
    verify(mFileSystemMasterClient).listStatus(file, FileSystemOptions.listStatusDefaults(mConf)
        .toBuilder().mergeFrom(listStatusOptions).build());

    verifyFilesystemContextAcquiredAndReleased();
  }