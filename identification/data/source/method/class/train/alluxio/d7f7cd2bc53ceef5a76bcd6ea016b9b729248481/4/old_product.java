public static void cleanDirectBuffer(ByteBuffer buffer) {
    if (buffer == null) {
      return;
    }
    if (buffer.isDirect()) {
      try {
        final Method getCleanerMethod = buffer.getClass().getMethod("cleaner");
        getCleanerMethod.setAccessible(true);
        final Object cleaner = getCleanerMethod.invoke(buffer);
        if (cleaner != null) {
          cleaner.getClass().getMethod("clean").invoke(cleaner);
        }
      } catch (Exception e) {
        LOG.warn("Fail to unmap direct buffer due to ", e);
        buffer = null;
      }
    }
  }