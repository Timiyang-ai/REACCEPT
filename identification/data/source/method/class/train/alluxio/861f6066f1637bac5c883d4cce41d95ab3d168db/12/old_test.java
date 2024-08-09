@Test
  public void mountTest() throws Exception {
    TachyonURI tachyonPath = new TachyonURI("/t");
    TachyonURI ufsPath = new TachyonURI("/u");
    MountOptions mountOptions = MountOptions.defaults();
    Mockito.doNothing().when(mFileSystemMasterClient).mount(tachyonPath, ufsPath);
    mFileSystem.mount(tachyonPath, ufsPath, mountOptions);
    Mockito.verify(mFileSystemMasterClient).mount(tachyonPath, ufsPath);
  }