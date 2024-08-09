public PersistenceState getPersistenceState(long fileId) throws FileDoesNotExistException {
    try (InodePath inodePath = mInodeTree.lockFullInodePath(fileId)) {
      return inodePath.getInode().getPersistenceState();
    }
  }