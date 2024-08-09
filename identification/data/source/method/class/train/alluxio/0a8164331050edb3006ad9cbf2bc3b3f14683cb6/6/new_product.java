public AlluxioURI getPath(long fileId) throws FileDoesNotExistException {
    try (InodePath inodePath = mInodeTree.lockFullInodePath(fileId, InodeTree.LockMode.READ)) {
      return mInodeTree.getPath(inodePath.getInode());
    }
  }