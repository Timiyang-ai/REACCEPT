public void unlockBlock(long sessionId, long blockId) throws BlockDoesNotExistException {
    mBlockStore.unlockBlock(sessionId, blockId);
  }