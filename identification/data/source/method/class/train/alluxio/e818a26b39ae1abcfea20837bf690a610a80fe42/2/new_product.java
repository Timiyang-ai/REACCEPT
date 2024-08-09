public void commitBlock(long sessionId, long blockId)
      throws BlockAlreadyExistsException, BlockDoesNotExistException, InvalidWorkerStateException,
      IOException, WorkerOutOfSpaceException {
    mBlockStore.commitBlock(sessionId, blockId);

    // TODO(calvin): Reconsider how to do this without heavy locking.
    // Block successfully committed, update master with new block metadata
    Long lockId = mBlockStore.lockBlock(sessionId, blockId);
    try {
      BlockMeta meta = mBlockStore.getBlockMeta(sessionId, blockId, lockId);
      BlockStoreLocation loc = meta.getBlockLocation();
      Long length = meta.getBlockSize();
      BlockStoreMeta storeMeta = mBlockStore.getBlockStoreMeta();
      Long bytesUsedOnTier = storeMeta.getUsedBytesOnTiers().get(loc.tierAlias());
      mBlockMasterClient.commitBlock(WorkerIdRegistry.getWorkerId(), bytesUsedOnTier,
          loc.tierAlias(), blockId, length);
    } catch (IOException | ConnectionFailedException e) {
      throw new IOException("Failed to commit block to master.", e);
    } finally {
      mBlockStore.unlockBlock(lockId);
    }
  }