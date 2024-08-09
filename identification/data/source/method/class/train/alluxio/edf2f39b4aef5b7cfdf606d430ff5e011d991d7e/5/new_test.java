  @Test
  public void accessBlock() throws Exception {
    long blockId = mRandom.nextLong();
    long sessionId = mRandom.nextLong();
    mBlockWorker.accessBlock(sessionId, blockId);
    verify(mBlockStore).accessBlock(sessionId, blockId);
  }