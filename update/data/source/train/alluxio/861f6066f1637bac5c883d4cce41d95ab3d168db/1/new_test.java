@Test
  public void mountTest() throws Exception {
    AlluxioURI tachyonPath = new AlluxioURI("/t");
    AlluxioURI ufsPath = new AlluxioURI("/u");
    MountOptions mountOptions = MountOptions.defaults();
    Mockito.doNothing().when(mFileSystemMasterClient).mount(tachyonPath, ufsPath);
    mFileSystem.mount(tachyonPath, ufsPath, mountOptions);
    Mockito.verify(mFileSystemMasterClient).mount(tachyonPath, ufsPath);
  }