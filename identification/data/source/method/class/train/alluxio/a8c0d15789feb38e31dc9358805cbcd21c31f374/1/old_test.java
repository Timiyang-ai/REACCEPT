@Test
  public void completeFileTest() throws Exception {
    mFileSystemMaster.create(ROOT_FILE_URI, sNestedFileOptions);
    writeBlockForFile(ROOT_FILE_URI);
    completeFile(ROOT_FILE_URI);

    // mFileSystemMaster.completeFile(multipleBlocksfileId);

    Assert.assertEquals(1, mCounters.get(MasterSource.COMPLETE_FILE_OPS).getCount());
    Assert.assertEquals(1, mCounters.get(MasterSource.FILES_COMPLETED).getCount());

    // trying to complete a completed file
    try {
      completeFile(ROOT_FILE_URI);
      Assert.fail("complete an already completed file must throw an exception");
    } catch (FileAlreadyCompletedException e) {
      // do nothing
    }

    mFileSystemMaster.getFileBlockInfoList(ROOT_FILE_URI);

    Assert.assertEquals(2, mCounters.get(MasterSource.COMPLETE_FILE_OPS).getCount());
    Assert.assertEquals(1, mCounters.get(MasterSource.FILES_COMPLETED).getCount());
  }