public void lockReadAndCheckFullPath(Inode parent, String name) throws InvalidPathException {
    lockReadAndCheckParent(parent);
    if (!mName.equals(name)) {
      unlockRead();
      throw new InvalidPathException(ExceptionMessage.PATH_INVALID_CONCURRENT_RENAME.getMessage());
    }
  }