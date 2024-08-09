public void resizeTempBlockMeta(TempBlockMeta tempBlockMeta, long newSize) {
    long oldSize = tempBlockMeta.getBlockSize();
    tempBlockMeta.setBlockSize(newSize);
    if (newSize > oldSize) {
      reserveSpace(newSize - oldSize);
    } else {
      LOG.error("Shrinking block, not supported!");
    }
  }