  @Test
  public void moveBlock() throws Exception {
    long blockId = mRandom.nextLong();
    long sessionId = mRandom.nextLong();
    String tierAlias = "MEM";
    BlockStoreLocation location = BlockStoreLocation.anyDirInTier(tierAlias);
    BlockStoreLocation existingLocation = mock(BlockStoreLocation.class);
    when(existingLocation.belongsTo(location)).thenReturn(false);
    BlockMeta meta = mock(BlockMeta.class);
    when(meta.getBlockLocation()).thenReturn(existingLocation);
    when(mBlockStore.getBlockMeta(eq(sessionId), eq(blockId), anyLong()))
        .thenReturn(meta);
    mBlockWorker.moveBlock(sessionId, blockId, tierAlias);
    verify(mBlockStore).moveBlock(sessionId, blockId, location);
  }