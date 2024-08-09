public synchronized boolean rename(int fileId, TachyonURI dstPath) throws IOException {
    return rename(fileId, TachyonURI.EMPTY_URI, dstPath);
  }