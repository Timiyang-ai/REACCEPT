public synchronized boolean delete(long fid, boolean recursive) throws IOException {
    return delete(fid, TachyonURI.EMPTY_URI, recursive);
  }