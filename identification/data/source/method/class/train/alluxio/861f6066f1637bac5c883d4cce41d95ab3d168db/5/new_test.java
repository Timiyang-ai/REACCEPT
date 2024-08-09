@Test
  public void loadMetadataTest() throws Exception {
    AlluxioURI file = new AlluxioURI("/file");
    LoadMetadataOptions loadMetadataOptions = LoadMetadataOptions.defaults().setRecursive(true);
    Mockito.doNothing().when(mFileSystemMasterClient).loadMetadata(file, loadMetadataOptions);
    mFileSystem.loadMetadata(file, loadMetadataOptions);
    Mockito.verify(mFileSystemMasterClient).loadMetadata(file, loadMetadataOptions);
  }