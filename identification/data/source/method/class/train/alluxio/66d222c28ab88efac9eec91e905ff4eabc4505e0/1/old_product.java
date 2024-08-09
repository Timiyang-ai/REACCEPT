public void releaseAccess(long sessionId, long blockId) {
    mLock.lock();
    try {
      Key key = new Key(sessionId, blockId);
      if (!mBlocks.containsKey(key)) {
        LOG.warn("Key (block ID: {}, session ID {}) is not found when releasing the UFS block.",
            blockId, sessionId);
      }
      mBlocks.remove(key);
      Set<Long> blockIds = mSessionIdToBlockIds.get(sessionId);
      if (blockIds != null) {
        blockIds.remove(blockId);
      }
      Set<Long> sessionIds = mBlockIdToSessionIds.get(blockId);
      if (sessionIds != null) {
        sessionIds.remove(sessionId);
      }
    } finally {
      mLock.unlock();
    }
  }