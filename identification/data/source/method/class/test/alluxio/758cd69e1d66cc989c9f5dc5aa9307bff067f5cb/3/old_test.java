  private MutableInode<?> getInodeByPath(String path) {
    try {
      return getInodeByPath(new AlluxioURI(path));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }