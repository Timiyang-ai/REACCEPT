@Test
  public void cancelBlockTest() throws Exception {
    final int fileId = mTfs.createFile(new TachyonURI("/testFile"));
    final int blockSize = (int) WORKER_CAPACITY_BYTES / 2;
    final long blockId = mTfs.getBlockId(fileId, 0);

    String filename = mWorkerServiceHandler.requestBlockLocation(USER_ID, blockId, blockSize);
    createBlockFile(filename, blockSize);
    mWorkerServiceHandler.cancelBlock(USER_ID, blockId);

    // The block should not exist after being cancelled
    Assert.assertFalse(new File(filename).exists());

    // The master should not have recorded any used space after the block is cancelled
    waitForHeartbeat();
    Assert.assertEquals(0, mMasterInfo.getUsedBytes());
  }