  @Test
  public void getTempBlockWriterRemote() throws Exception {
    long blockId = mRandom.nextLong();
    long sessionId = mRandom.nextLong();
    mBlockWorker.getTempBlockWriterRemote(sessionId, blockId);
    verify(mBlockStore).getBlockWriter(sessionId, blockId);
  }