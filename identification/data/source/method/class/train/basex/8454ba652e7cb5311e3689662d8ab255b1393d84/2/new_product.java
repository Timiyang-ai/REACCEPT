@Override
  public int create(final String path, final int mode) {
    if(!isFile(mode)) return -1;
    return createFile(path, mode);
  }