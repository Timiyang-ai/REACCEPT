public static synchronized void cleanDirectBuffer(ByteBuffer buffer) {
    Preconditions.checkNotNull(buffer, "buffer");
    Preconditions.checkArgument(buffer.isDirect(), "buffer isn't a DirectByteBuffer");
    try {
      if (sByteBufferCleanerMethod == null) {
        sByteBufferCleanerMethod = buffer.getClass().getMethod("cleaner");
        sByteBufferCleanerMethod.setAccessible(true);
      }
      final Object cleaner = sByteBufferCleanerMethod.invoke(buffer);
      if (cleaner == null) {
        if (buffer.capacity() > 0) {
          LOG.warn("Failed to get cleaner for ByteBuffer: {}", buffer.getClass().getName());
        }
        // The cleaner could be null when the buffer is initialized as zero capacity.
        return;
      }
      if (sCleanerCleanMethod == null) {
        sCleanerCleanMethod = cleaner.getClass().getMethod("clean");
      }
      sCleanerCleanMethod.invoke(cleaner);
    } catch (Exception e) {
      LOG.warn("Failed to unmap direct ByteBuffer: {}, error message: {}",
                buffer.getClass().getName(), e.getMessage());
    } finally {
      // Force to drop reference to the buffer to clean
      buffer = null;
    }
  }