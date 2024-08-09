@Test
  public void commitBlock() throws Exception {
    long blockId = mRandom.nextLong();
    long length = mRandom.nextLong();
    long lockId = mRandom.nextLong();
    long sessionId = mRandom.nextLong();
    long usedBytes = mRandom.nextLong();
    String tierAlias = "MEM";
    HashMap<String, Long> usedBytesOnTiers = new HashMap<>();
    usedBytesOnTiers.put(tierAlias, usedBytes);
    BlockMeta blockMeta = PowerMockito.mock(BlockMeta.class);
    BlockStoreLocation blockStoreLocation = PowerMockito.mock(BlockStoreLocation.class);
    BlockStoreMeta blockStoreMeta = mock(BlockStoreMeta.class);

    when(mBlockStore.lockBlock(sessionId, blockId)).thenReturn(lockId);
    when(mBlockStore.getBlockMeta(sessionId, blockId, lockId)).thenReturn(blockMeta);
    when(mBlockStore.getBlockStoreMeta()).thenReturn(blockStoreMeta);
    when(mBlockStore.getBlockStoreMetaFull()).thenReturn(blockStoreMeta);
    when(blockMeta.getBlockLocation()).thenReturn(blockStoreLocation);
    when(blockStoreLocation.tierAlias()).thenReturn(tierAlias);
    when(blockMeta.getBlockSize()).thenReturn(length);
    when(blockStoreMeta.getUsedBytesOnTiers()).thenReturn(usedBytesOnTiers);

    mBlockWorker.commitBlock(sessionId, blockId);
    verify(mBlockMasterClient).commitBlock(anyLong(), eq(usedBytes), eq(tierAlias), eq(blockId),
        eq(length));
    verify(mBlockStore).unlockBlock(lockId);
  }