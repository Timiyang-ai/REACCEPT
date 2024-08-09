  private FileStatus getFileStatus(String path) {
    return new FileStatus(10, false, 3, 1000, 10000, new Path(path));
  }