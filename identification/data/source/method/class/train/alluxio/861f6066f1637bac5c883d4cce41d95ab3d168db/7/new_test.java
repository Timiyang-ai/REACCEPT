@Test
  public void unmountTest() throws Exception {
    AlluxioURI path = new AlluxioURI("/");
    UnmountOptions unmountOptions = UnmountOptions.defaults();
    Mockito.doNothing().when(mFileSystemMasterClient).unmount(path);
    mFileSystem.unmount(path, unmountOptions);
    Mockito.verify(mFileSystemMasterClient).unmount(path);
  }