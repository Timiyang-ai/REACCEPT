@Test
  public void unmount() throws Exception {
    AlluxioURI alluxioURI = new AlluxioURI("/hello");
    AlluxioURI ufsURI = createTempUfsDir("ufs/hello");
    mFileSystemMaster.mount(alluxioURI, ufsURI, MountContext.defaults());
    mFileSystemMaster.createDirectory(alluxioURI.join("dir"), CreateDirectoryContext
        .defaults().setPersisted(true));
    mFileSystemMaster.unmount(alluxioURI);
    // after unmount, ufs path under previous mount point should still exist
    File file = new File(ufsURI.join("dir").toString());
    assertTrue(file.exists());
    // after unmount, alluxio path under previous mount point should not exist
    mThrown.expect(FileDoesNotExistException.class);
    mFileSystemMaster.getFileInfo(alluxioURI.join("dir"), GET_STATUS_CONTEXT);
  }