  @Test
  public void readBlockRemote() throws Exception {
    long blockId = mRandom.nextLong();
    long sessionId = mRandom.nextLong();
    long lockId = mRandom.nextLong();

    mBlockWorker.readBlockRemote(sessionId, blockId, lockId);
    verify(mBlockStore).getBlockReader(sessionId, blockId, lockId);
  }