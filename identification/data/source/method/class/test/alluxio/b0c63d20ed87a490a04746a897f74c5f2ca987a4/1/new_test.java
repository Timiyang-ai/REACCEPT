@Test
  public void cacheBlockTest() throws Exception {
    ClientOptions options = new ClientOptions.Builder(new TachyonConf()).build();
    mTfs.getOutStream(new TachyonURI("/testFile"), options);
    TachyonFile file = mTfs.open(new TachyonURI("/testFile"));

    final int blockSize = (int) WORKER_CAPACITY_BYTES / 10;
    // Construct the block ids for the file.
    final long blockId0 = BlockId.createBlockId(BlockId.getContainerId(file.getFileId()), 0);
    final long blockId1 = BlockId.createBlockId(BlockId.getContainerId(file.getFileId()), 1);

    String filename = mWorkerServiceHandler.requestBlockLocation(USER_ID, blockId0, blockSize);
    createBlockFile(filename, blockSize);
    mWorkerServiceHandler.cacheBlock(USER_ID, blockId0);

    // The master should be immediately updated with the persisted block
    Assert.assertEquals(blockSize, mBlockMasterClient.getUsedBytes());

    // Attempting to cache a non existent block should throw an exception
    Exception exception = null;
    try {
      mWorkerServiceHandler.cacheBlock(USER_ID, blockId1);
    } catch (TException e) {
      exception = e;
    }
    Assert.assertNotNull(exception);
  }