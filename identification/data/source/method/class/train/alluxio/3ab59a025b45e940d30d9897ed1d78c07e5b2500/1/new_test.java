  @Test
  public void getUfsInfo() throws Exception {
    FileInfo alluxioRootInfo =
        mFileSystemMaster.getFileInfo(new AlluxioURI("alluxio://"), GET_STATUS_CONTEXT);
    UfsInfo ufsRootInfo = mFileSystemMaster.getUfsInfo(alluxioRootInfo.getMountId());
    assertEquals(mUnderFS, ufsRootInfo.getUri().getPath());
    assertTrue(ufsRootInfo.getMountOptions().getPropertiesMap().isEmpty());
  }