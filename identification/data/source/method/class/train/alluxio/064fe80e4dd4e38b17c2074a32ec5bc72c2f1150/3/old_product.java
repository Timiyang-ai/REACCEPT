public String createBlock(long sessionId, long blockId, String tierAlias, long initialBytes)
      throws BlockAlreadyExistsException, WorkerOutOfSpaceException, IOException {
    BlockStoreLocation loc = BlockStoreLocation.anyDirInTier(tierAlias);
    TempBlockMeta createdBlock = mBlockStore.createBlockMeta(sessionId, blockId, loc, initialBytes);
    return createdBlock.getPath();
  }