@Test
  public void listStatus() throws Exception {
    AlluxioURI file = new AlluxioURI("/file");
    List<URIStatus> infos = new ArrayList<>();
    infos.add(new URIStatus(new FileInfo()));
    ListStatusOptions listStatusOptions = ListStatusOptions.defaults();
    when(mFileSystemMasterClient.listStatus(file, listStatusOptions)).thenReturn(infos);
    assertSame(infos, mFileSystem.listStatus(file, listStatusOptions));
    verify(mFileSystemMasterClient).listStatus(file, listStatusOptions);

    verifyFilesystemContextAcquiredAndReleased();
  }