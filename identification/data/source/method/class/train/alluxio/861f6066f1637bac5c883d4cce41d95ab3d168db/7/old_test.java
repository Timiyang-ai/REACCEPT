@Test
  public void unmountTest() throws Exception {
    TachyonURI path = new TachyonURI("/");
    UnmountOptions unmountOptions = UnmountOptions.defaults();
    Mockito.doNothing().when(mFileSystemMasterClient).unmount(path);
    mFileSystem.unmount(path, unmountOptions);
    Mockito.verify(mFileSystemMasterClient).unmount(path);
  }