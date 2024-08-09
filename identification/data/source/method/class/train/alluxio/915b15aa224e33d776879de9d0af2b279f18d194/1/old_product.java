public TempBlockMeta createTempBlockMeta(long sessionID, long blockID, long initialBlockSize) {
    return new TempBlockMeta(sessionID, blockID, initialBlockSize, mDir);
  }