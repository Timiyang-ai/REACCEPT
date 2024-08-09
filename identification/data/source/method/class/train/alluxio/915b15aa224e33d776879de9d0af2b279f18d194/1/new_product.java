public TempBlockMeta createTempBlockMeta(long sessionId, long blockId, long initialBlockSize) {
    return new TempBlockMeta(sessionId, blockId, initialBlockSize, mDir);
  }