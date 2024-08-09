@Override
  protected boolean isReusable(Bitmap value) {
    Preconditions.checkNotNull(value);
    return !value.isRecycled() &&
        value.isMutable() &&
        Bitmaps.BITMAP_CONFIG.equals(value.getConfig());
  }