public AlluxioURI getPath(long fileId) throws FileDoesNotExistException {
    try (InodePath inodePath = mInodeTree.lockFullInodePath(fileId, InodeTree.LockMode.READ)) {
      // the path is already locked.
      return mInodeTree.getPath(inodePath.getInode());
    }
  }