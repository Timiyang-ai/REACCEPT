  @Test
  public void requestSpace() throws Exception {
    long blockId = mRandom.nextLong();
    long sessionId = mRandom.nextLong();
    long additionalBytes = mRandom.nextLong();
    mBlockWorker.requestSpace(sessionId, blockId, additionalBytes);
    verify(mBlockStore).requestSpace(sessionId, blockId, additionalBytes);
  }