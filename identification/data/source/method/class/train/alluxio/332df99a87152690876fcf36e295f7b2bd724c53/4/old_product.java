public void resizeTempBlockMeta(TempBlockMeta tempBlockMeta, long newSize) throws IOException {
    long oldSize = tempBlockMeta.getBlockSize();
    tempBlockMeta.setBlockSize(newSize);
    if (newSize > oldSize) {
      reserveSpace(newSize - oldSize);
    } else if (newSize < oldSize) {
      throw new IOException("Shrinking block, not supported!");
    }
  }