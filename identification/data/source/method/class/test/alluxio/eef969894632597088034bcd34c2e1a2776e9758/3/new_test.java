@Test
  public void mount() throws Exception {
    AlluxioURI alluxioPath = new AlluxioURI("/t");
    AlluxioURI ufsPath = new AlluxioURI("/u");
    MountPOptions mountOptions = MountPOptions.getDefaultInstance();
    doNothing().when(mFileSystemMasterClient).mount(alluxioPath, ufsPath,
        FileSystemOptions.mountDefaults(mConf).toBuilder().mergeFrom(mountOptions).build());
    mFileSystem.mount(alluxioPath, ufsPath, mountOptions);
    verify(mFileSystemMasterClient).mount(alluxioPath, ufsPath,
        FileSystemOptions.mountDefaults(mConf).toBuilder().mergeFrom(mountOptions).build());

    verifyFilesystemContextAcquiredAndReleased();
  }