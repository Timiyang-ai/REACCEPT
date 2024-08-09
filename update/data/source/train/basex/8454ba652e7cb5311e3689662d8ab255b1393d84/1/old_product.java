@Override
  public int create(final String path, final int mode) {
    try {
      if(!isFile(mode)) return -1;
      int pre = insert(path, mode);
      return (pre == -1) ? -1 : data.id(pre);
    } catch(QueryException e) {
      e.printStackTrace();
      return -1;
    }
  }