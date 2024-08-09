  @Test
  public void getVolatileBlockMeta() throws Exception {
    long blockId = mRandom.nextLong();
    mBlockWorker.getVolatileBlockMeta(blockId);
    verify(mBlockStore).getVolatileBlockMeta(blockId);
  }