public void releaseAccess(long sessionId, long blockId) {
    mLock.lock();
    try {
      Key key = new Key(sessionId, blockId);
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