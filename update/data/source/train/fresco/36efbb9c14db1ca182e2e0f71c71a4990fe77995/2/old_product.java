@Override
  protected int getBucketedSizeForValue(Bitmap value) {
    Preconditions.checkNotNull(value);
    final int allocationByteCount = value.getAllocationByteCount();
    return allocationByteCount / Bitmaps.BYTES_PER_PIXEL;
  }