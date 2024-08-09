public PersistenceState getPersistenceState(long fileId) throws FileDoesNotExistException {
    try (InodePath inodePath = mInodeTree.getInodePath(fileId)) {
      return inodePath.getInode().getPersistenceState();
    }
  }