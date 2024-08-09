  @Test
  public void unlockBlock() throws Exception {
    long lockId = mRandom.nextLong();
    long sessionId = mRandom.nextLong();
    long blockId = mRandom.nextLong();

    mBlockWorker.unlockBlock(lockId);
    verify(mBlockStore).unlockBlock(lockId);

    mBlockWorker.unlockBlock(sessionId, blockId);
    verify(mBlockStore).unlockBlock(sessionId, blockId);
  }