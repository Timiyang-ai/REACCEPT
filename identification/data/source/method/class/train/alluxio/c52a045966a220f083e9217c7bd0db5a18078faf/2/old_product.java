public synchronized void resizeTempBlockMeta(TempBlockMeta tempBlockMeta, long newSize)
      throws InvalidStateException {
    StorageDir dir = tempBlockMeta.getParentDir();
    dir.resizeTempBlockMeta(tempBlockMeta, newSize);
  }