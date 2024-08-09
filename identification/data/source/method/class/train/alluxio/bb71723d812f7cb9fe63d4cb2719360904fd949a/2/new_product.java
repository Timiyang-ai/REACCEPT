@Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof BlockStoreLocation)) {
      return false;
    }
    BlockStoreLocation that = (BlockStoreLocation) o;
    return mTierAlias.equals(that.mTierAlias) && mDirIndex == that.mDirIndex
        && mMediumType.equals(that.mMediumType);
  }