public synchronized void resizeTempBlockMeta(TempBlockMeta tempBlockMeta, long newSize)
      throws FailedPreconditionException {
    StorageDir dir = tempBlockMeta.getParentDir();
    dir.resizeTempBlockMeta(tempBlockMeta, newSize);
  }