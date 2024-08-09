public void lockWriteAndCheckFullPath(Inode parent, String name)
      throws InvalidPathException {
    lockWriteAndCheckParent(parent);
    if (!mName.equals(name)) {
      unlockWrite();
      throw new InvalidPathException(ExceptionMessage.PATH_INVALID_CONCURRENT_RENAME.getMessage());
    }
  }