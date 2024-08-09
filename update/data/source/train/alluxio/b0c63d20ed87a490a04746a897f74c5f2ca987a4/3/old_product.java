public synchronized boolean delete(int fid, boolean recursive) throws IOException {
    return delete(fid, TachyonURI.EMPTY_URI, recursive);
  }