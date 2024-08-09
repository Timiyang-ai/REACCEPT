public PersistenceState getPersistenceState(long fileId) throws FileDoesNotExistException {
    try (InodePath inodePath = mInodeTree.lockFullInodePath(fileId, InodeTree.LockMode.READ)) {
      return inodePath.getInode().getPersistenceState();
    }
  }