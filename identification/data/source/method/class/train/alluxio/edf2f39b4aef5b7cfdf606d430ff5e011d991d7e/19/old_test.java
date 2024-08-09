  @Test
  public void removeBlock() throws Exception {
    long blockId = mRandom.nextLong();
    long sessionId = mRandom.nextLong();
    mBlockWorker.removeBlock(sessionId, blockId);
    verify(mBlockStore).removeBlock(sessionId, blockId);
  }