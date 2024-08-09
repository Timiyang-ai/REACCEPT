  @Test
  public void hasBlockMeta() {
    long blockId = mRandom.nextLong();
    mBlockWorker.hasBlockMeta(blockId);
    verify(mBlockStore).hasBlockMeta(blockId);
  }