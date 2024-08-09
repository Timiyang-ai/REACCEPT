public synchronized void resizeTempBlockMeta(TempBlockMeta tempBlockMeta, long newSize)
      throws IOException {
    StorageDir dir = tempBlockMeta.getParentDir();
    dir.resizeTempBlockMeta(tempBlockMeta, newSize);
  }