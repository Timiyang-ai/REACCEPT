public void releaseAccess(long sessionId, long blockId) {
    try (LockResource lr = new LockResource(mLock)) {
      Key key = new Key(sessionId, blockId);
      if (!mBlocks.containsKey(key)) {
        LOG.warn("Key (block ID: {}, session ID {}) is not found when releasing the UFS block.",
            blockId, sessionId);
      }
      mBlocks.remove(key);
      Set<Long> blockIds = mSessionIdToBlockIds.get(sessionId);
      if (blockIds != null) {
        blockIds.remove(blockId);
        if (blockIds.isEmpty()) {
          mSessionIdToBlockIds.remove(sessionId);
        }
      }
      Set<Long> sessionIds = mBlockIdToSessionIds.get(blockId);
      if (sessionIds != null) {
        sessionIds.remove(sessionId);
        if (sessionIds.isEmpty()) {
          mBlockIdToSessionIds.remove(blockId);
        }
      }
    }
  }