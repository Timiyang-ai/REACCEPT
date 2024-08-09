@Test
  public void loadMetadata() throws Exception {
    AlluxioURI file = new AlluxioURI("/file");
    LoadMetadataOptions loadMetadataOptions = LoadMetadataOptions.defaults().setRecursive(true);
    doNothing().when(mFileSystemMasterClient).loadMetadata(file, loadMetadataOptions);
    mFileSystem.loadMetadata(file, loadMetadataOptions);
    verify(mFileSystemMasterClient).loadMetadata(file, loadMetadataOptions);
  }