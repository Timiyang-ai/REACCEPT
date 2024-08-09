public void moveBlock(long sessionId, long blockId, String tierAlias)
      throws BlockDoesNotExistException, BlockAlreadyExistsException, InvalidWorkerStateException,
      WorkerOutOfSpaceException, IOException {
    BlockStoreLocation dst = BlockStoreLocation.anyDirInTier(tierAlias);
    mBlockStore.moveBlock(sessionId, blockId, dst);
  }