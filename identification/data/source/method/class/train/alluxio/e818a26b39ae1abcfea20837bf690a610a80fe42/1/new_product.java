public void commitBlock(long sessionId, long blockId)
      throws BlockAlreadyExistsException, BlockDoesNotExistException, InvalidWorkerStateException,
      IOException, WorkerOutOfSpaceException {
    // NOTE: this may be invoked multiple times due to retry on client side.
    // TODO(binfan): find a better way to handle retry logic
    try {
      mBlockStore.commitBlock(sessionId, blockId);
    } catch (BlockAlreadyExistsException e) {
      LOG.debug("Block {} has been in block store, this could be a retry due to master-side RPC "
          + "failure, therefore ignore the exception", blockId, e);
    }

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
    } catch (AlluxioTException | IOException | ConnectionFailedException e) {
      throw new IOException("Failed to commit block " + blockId + " to master.", e);
    } finally {
      mBlockStore.unlockBlock(lockId);
    }
  }