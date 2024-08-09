@Test
  public void mount() throws Exception {
    AlluxioURI alluxioPath = new AlluxioURI("/t");
    AlluxioURI ufsPath = new AlluxioURI("/u");
    MountOptions mountOptions = MountOptions.defaults();
    doNothing().when(mFileSystemMasterClient).mount(alluxioPath, ufsPath, mountOptions);
    mFileSystem.mount(alluxioPath, ufsPath, mountOptions);
    verify(mFileSystemMasterClient).mount(alluxioPath, ufsPath, mountOptions);

    verifyFilesystemContextAcquiredAndReleased();
  }