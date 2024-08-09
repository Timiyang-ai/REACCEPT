public void resizeTempBlockMeta(TempBlockMeta tempBlockMeta, long newSize)
      throws InvalidStateException {
    long oldSize = tempBlockMeta.getBlockSize();
    if (newSize > oldSize) {
      reserveSpace(newSize - oldSize, false);
      tempBlockMeta.setBlockSize(newSize);
    } else if (newSize < oldSize) {
      throw new InvalidStateException("Shrinking block, not supported!");
    }
  }