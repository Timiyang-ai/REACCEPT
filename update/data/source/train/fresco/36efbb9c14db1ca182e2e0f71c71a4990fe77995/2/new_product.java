@Override
  protected int getBucketedSizeForValue(Bitmap value) {
    Preconditions.checkNotNull(value);
    return value.getAllocationByteCount();
  }