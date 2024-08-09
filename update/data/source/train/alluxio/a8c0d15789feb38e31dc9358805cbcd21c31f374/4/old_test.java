@Test
  public void getFileInfoTest() throws Exception {
    long fileId = mFileSystemMaster.create(ROOT_FILE_URI, sNestedFileOptions);

    mFileSystemMaster.getFileInfo(fileId);

    Assert.assertEquals(1, mCounters.get(MasterSource.GET_FILE_INFO_OPS).getCount());
    Assert.assertEquals(1, mCounters.get(MasterSource.FILE_INFOS_GOT).getCount());

    // trying to get non-existent file info
    try {
      mFileSystemMaster.getFileInfo(-1);
      Assert.fail("get file info for a non existing file must throw an exception");
    } catch (FileDoesNotExistException e) {
      // do nothing
    }

    Assert.assertEquals(2, mCounters.get(MasterSource.GET_FILE_INFO_OPS).getCount());
    Assert.assertEquals(1, mCounters.get(MasterSource.FILE_INFOS_GOT).getCount());
  }