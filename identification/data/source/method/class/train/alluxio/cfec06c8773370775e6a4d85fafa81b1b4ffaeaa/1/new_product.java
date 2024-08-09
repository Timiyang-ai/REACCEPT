public boolean isFullyInMemory(TachyonURI path)
      throws FileDoesNotExistException, InvalidPathException {
    Inode inode = mInodeTree.getInodeByPath(path);
    return getInMemoryPercentage(inode) == 100;
  }