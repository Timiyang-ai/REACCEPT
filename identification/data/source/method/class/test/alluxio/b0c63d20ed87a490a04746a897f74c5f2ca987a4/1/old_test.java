@Test
  public void cacheBlockTest() throws Exception {
    final int fileId = mTfs.createFile(new TachyonURI("/testFile"));
    final int blockSize = (int) WORKER_CAPACITY_BYTES / 10;
    final long blockId0 = mTfs.getBlockId(fileId, 0);
    final long blockId1 = mTfs.getBlockId(fileId, 1);

    String filename = mWorkerServiceHandler.requestBlockLocation(USER_ID, blockId0, blockSize);
    createBlockFile(filename, blockSize);
    mWorkerServiceHandler.cacheBlock(USER_ID, blockId0);

    // The master should be immediately updated with the persisted block
    Assert.assertEquals(blockSize, mMasterInfo.getUsedBytes());

    // Attempting to cache a non existent block should throw an exception
    Exception exception = null;
    try {
      mWorkerServiceHandler.cacheBlock(USER_ID, blockId1);
    } catch (TException e) {
      exception = e;
    }
    Assert.assertNotNull(exception);
  }