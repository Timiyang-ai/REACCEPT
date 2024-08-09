private boolean isFullyInMemory(InodeFile inode) {
    try {
      return getInMemoryPercentage(inode) == 100;
    } catch (FileDoesNotExistException e) {
      // should never happen
      throw Throwables.propagate(e);
    }
  }