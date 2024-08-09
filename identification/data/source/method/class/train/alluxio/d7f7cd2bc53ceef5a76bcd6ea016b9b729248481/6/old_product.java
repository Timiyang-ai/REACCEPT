public static void cleanDirectBuffer(ByteBuffer buffer) {
    if (buffer == null) {
      return;
    }
    if (buffer.isDirect()) {
      try {
        if (sCleanerMethod == null) {
          sCleanerMethod = buffer.getClass().getMethod("cleaner");
          sCleanerMethod.setAccessible(true);
        }
        final Object cleaner = sCleanerMethod.invoke(buffer);
        if (cleaner != null) {
          if (sCleanMethod == null) {
            sCleanMethod = cleaner.getClass().getMethod("clean");
          }
          sCleanMethod.invoke(cleaner);
        }
      } catch (Exception e) {
        LOG.warn("Fail to unmap direct buffer due to ", e);
        buffer = null;
      }
    }
  }