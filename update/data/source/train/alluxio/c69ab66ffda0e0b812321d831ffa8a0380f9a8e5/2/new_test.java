@Test
  public void mountTest() throws Exception {
    AlluxioURI alluxioPath = new AlluxioURI("/t");
    AlluxioURI ufsPath = new AlluxioURI("/u");
    MountOptions mountOptions = MountOptions.defaults();
    Mockito.doNothing().when(mFileSystemMasterClient).mount(alluxioPath, ufsPath);
    mFileSystem.mount(alluxioPath, ufsPath, mountOptions);
    Mockito.verify(mFileSystemMasterClient).mount(alluxioPath, ufsPath);
  }