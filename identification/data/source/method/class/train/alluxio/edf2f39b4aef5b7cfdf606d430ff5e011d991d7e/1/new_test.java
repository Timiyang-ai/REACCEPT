  @Test
  public void freeSpace() throws Exception {
    long sessionId = mRandom.nextLong();
    long availableBytes = mRandom.nextLong();
    String tierAlias = "MEM";
    BlockStoreLocation location = BlockStoreLocation.anyDirInTier(tierAlias);
    mBlockWorker.freeSpace(sessionId, availableBytes, tierAlias);
    verify(mBlockStore).freeSpace(sessionId, availableBytes, location);
  }