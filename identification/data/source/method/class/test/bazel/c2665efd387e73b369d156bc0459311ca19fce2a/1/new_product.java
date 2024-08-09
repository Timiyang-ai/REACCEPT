public void reset() throws IOException {
    if (data != null) {
      data.close();
    }
    data = null;
    offset = 0;
    initialized = false;
    chunkCache = null;
  }