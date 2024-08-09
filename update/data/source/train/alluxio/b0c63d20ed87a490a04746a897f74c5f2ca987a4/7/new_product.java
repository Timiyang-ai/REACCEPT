public synchronized boolean rename(long fileId, TachyonURI dstPath) throws IOException {
    return rename(fileId, TachyonURI.EMPTY_URI, dstPath);
  }