public PersistenceState getPersistenceState(long fileId) throws FileDoesNotExistException {
    synchronized (mInodeTree) {
      Inode<?> inode = mInodeTree.getInodeById(fileId);
      return inode.getPersistenceState();
    }
  }