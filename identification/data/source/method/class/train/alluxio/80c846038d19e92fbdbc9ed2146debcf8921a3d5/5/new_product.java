public void acquireAccess(long sessionId, long blockId, OpenUfsBlockOptions options)
      throws BlockAlreadyExistsException, UfsBlockAccessTokenUnavailableException {
    UnderFileSystemBlockMeta blockMeta = new UnderFileSystemBlockMeta(sessionId, blockId, options);
    mLock.lock();
    try {
      Set<Long> sessionIds = mBlockIdToSessionIds.get(blockId);
      if (sessionIds != null && sessionIds.size() >= options.getMaxUfsReadConcurrency()) {
        throw new UfsBlockAccessTokenUnavailableException(
            ExceptionMessage.UFS_BLOCK_ACCESS_TOKEN_UNAVAILABLE, sessionIds.size(), blockId,
            blockMeta.getUnderFileSystemPath());
      }
      if (sessionIds == null) {
        sessionIds = new HashSet<>();
        mBlockIdToSessionIds.put(blockId, sessionIds);
      }
      sessionIds.add(sessionId);

      Set<Long> blockIds = mSessionIdToBlockIds.get(sessionId);
      if (blockIds == null) {
        blockIds = new HashSet<>();
        mSessionIdToBlockIds.put(sessionId, blockIds);
      }
      blockIds.add(blockId);
    } finally {
      mLock.unlock();
    }
    if (mBlocks.putIfAbsent(new Key(sessionId, blockId), new BlockInfo(blockMeta)) != null) {
      throw new BlockAlreadyExistsException(ExceptionMessage.UFS_BLOCK_ALREADY_EXISTS_FOR_SESSION,
          blockId, blockMeta.getUnderFileSystemPath(), sessionId);
    }
  }