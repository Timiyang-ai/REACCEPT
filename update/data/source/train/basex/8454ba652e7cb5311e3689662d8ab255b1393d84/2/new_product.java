@Override
  public int mkdir(final String path, final int mode) {
    if(!isDir(mode)) return -1;
    return createFile(path, mode);
  }