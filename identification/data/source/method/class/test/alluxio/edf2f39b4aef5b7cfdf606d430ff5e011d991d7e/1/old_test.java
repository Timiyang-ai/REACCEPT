  @Test
  public void readBlock() throws Exception {
    long blockId = mRandom.nextLong();
    long sessionId = mRandom.nextLong();
    long lockId = mRandom.nextLong();
    long blockSize = mRandom.nextLong();
    StorageDir storageDir = mock(StorageDir.class);
    when(storageDir.getDirPath()).thenReturn("/tmp");
    BlockMeta meta = new BlockMeta(blockId, blockSize, storageDir);
    when(mBlockStore.getBlockMeta(sessionId, blockId, lockId)).thenReturn(meta);

    mBlockWorker.readBlock(sessionId, blockId, lockId);
    verify(mBlockStore).getBlockMeta(sessionId, blockId, lockId);
    assertEquals(PathUtils.concatPath("/tmp", blockId),
        mBlockWorker.readBlock(sessionId, blockId, lockId));
  }