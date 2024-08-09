  @Test
  public void createBlock() throws Exception {
    long blockId = mRandom.nextLong();
    long initialBytes = mRandom.nextLong();
    long sessionId = mRandom.nextLong();
    String tierAlias = "MEM";
    BlockStoreLocation location = BlockStoreLocation.anyDirInTier(tierAlias);
    StorageDir storageDir = mock(StorageDir.class);
    TempBlockMeta meta = new TempBlockMeta(sessionId, blockId, initialBytes, storageDir);

    when(mBlockStore.createBlock(sessionId, blockId, location, initialBytes)).thenReturn(meta);
    when(storageDir.getDirPath()).thenReturn("/tmp");
    assertEquals(
        PathUtils.concatPath("/tmp", ".tmp_blocks", sessionId % 1024,
            String.format("%x-%x", sessionId, blockId)),
        mBlockWorker.createBlock(sessionId, blockId, tierAlias, "", initialBytes));
  }