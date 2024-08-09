public boolean delete(AlluxioURI path, boolean recursive) throws IOException {
    try {
      mFileSystem.delete(path, DeleteOptions.defaults().setRecursive(recursive));
      return true;
    } catch (Exception e) {
      throw new IOException(e);
    }
  }