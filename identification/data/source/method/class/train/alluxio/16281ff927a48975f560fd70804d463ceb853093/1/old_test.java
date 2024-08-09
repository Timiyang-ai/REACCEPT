@Test
  public void addCheckpointTest() throws Exception {
    final int fileId = mTfs.createFile(new TachyonURI("/testFile"));
    final int blockSize = (int) WORKER_CAPACITY_BYTES / 10;

    String tmpFolder = mWorkerServiceHandler.getUserUfsTempFolder(USER_ID);
    UnderFileSystem ufs = UnderFileSystem.get(tmpFolder, mMasterTachyonConf);
    ufs.mkdirs(tmpFolder, true);
    String filename = CommonUtils.concatPath(tmpFolder, fileId);
    OutputStream out = ufs.create(filename);
    out.write(TestUtils.getIncreasingByteArray(blockSize));
    out.close();
    mWorkerServiceHandler.addCheckpoint(USER_ID, fileId);

    // No space should be used in Tachyon, but the file should be complete
    Assert.assertEquals(0, mMasterInfo.getUsedBytes());
    Assert.assertTrue(mTfs.getFile(fileId).isComplete());
  }