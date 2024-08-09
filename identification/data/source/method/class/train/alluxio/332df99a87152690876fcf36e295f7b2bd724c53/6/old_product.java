public void resizeTempBlockMeta(TempBlockMeta tempBlockMeta, long newSize)
      throws InvalidStateException {
    long oldSize = tempBlockMeta.getBlockSize();
    tempBlockMeta.setBlockSize(newSize);
    if (newSize > oldSize) {
      reserveSpace(newSize - oldSize, false);
    } else if (newSize < oldSize) {
      throw new InvalidStateException("Shrinking block, not supported!");
    }
  }