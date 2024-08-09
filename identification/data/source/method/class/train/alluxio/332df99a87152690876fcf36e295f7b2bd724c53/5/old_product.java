public void resizeTempBlockMeta(TempBlockMeta tempBlockMeta, long newSize)
      throws FailedPreconditionException {
    long oldSize = tempBlockMeta.getBlockSize();
    tempBlockMeta.setBlockSize(newSize);
    if (newSize > oldSize) {
      reserveSpace(newSize - oldSize, false);
    } else if (newSize < oldSize) {
      throw new FailedPreconditionException("Shrinking block, not supported!");
    }
  }