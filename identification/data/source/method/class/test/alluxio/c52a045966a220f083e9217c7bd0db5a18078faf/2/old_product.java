public synchronized void resizeTempBlockMeta(TempBlockMeta tempBlockMeta, long newSize) {
    StorageDir dir = tempBlockMeta.getParentDir();
    dir.resizeTempBlockMeta(tempBlockMeta, newSize);
  }