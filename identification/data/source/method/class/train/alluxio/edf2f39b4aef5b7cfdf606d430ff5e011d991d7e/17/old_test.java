  @Test
  public void abortBlock() throws Exception {
    long blockId = mRandom.nextLong();
    long sessionId = mRandom.nextLong();
    mBlockWorker.abortBlock(sessionId, blockId);
    verify(mBlockStore).abortBlock(sessionId, blockId);
  }