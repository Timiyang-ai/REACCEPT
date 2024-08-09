public static void cleanDirectBuffer(ByteBuffer buffer) {
    Preconditions.checkNotNull(buffer);
    Preconditions.checkArgument(buffer.isDirect(), "buffer isn't a DirectByteBuffer");
    try {
      if (sByteBufferCleanerMethod == null) {
        sByteBufferCleanerMethod = buffer.getClass().getMethod("cleaner");
        sByteBufferCleanerMethod.setAccessible(true);
      }
      final Object cleaner = sByteBufferCleanerMethod.invoke(buffer);
      if (cleaner == null) {
        LOG.warn("Failed to get cleaner for ByteBuffer: {}", buffer.getClass().getName());
        return;
      }
      if (sCleanerCleanMethod == null) {
        sCleanerCleanMethod = cleaner.getClass().getMethod("clean");
      }
      sCleanerCleanMethod.invoke(cleaner);
    } catch (Exception e) {
      LOG.warn("Failed to unmap direct ByteBuffer: {}", buffer.getClass().getName(), e);
    } finally {
      // Force to drop reference to the buffer to clean
      buffer = null;
    }
  }