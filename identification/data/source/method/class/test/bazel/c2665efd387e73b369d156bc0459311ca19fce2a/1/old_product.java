public void reset() throws IOException {
    if (data != null) {
      data.close();
    }
    try {
      data = dataSupplier.get();
    } catch (RuntimeException e) {
      Throwables.propagateIfPossible(e.getCause(), IOException.class);
      throw e;
    }
    offset = 0;
    chunkCache = null;
  }