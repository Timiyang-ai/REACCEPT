@Override
  public int mkdir(final String path, final int mode) {
    try {
      if(!isDir(mode)) return -1;
      return insert(path, mode) == -1 ? -1 : 0;
    } catch(QueryException e) {
      e.printStackTrace();
      return -1;
    }
  }