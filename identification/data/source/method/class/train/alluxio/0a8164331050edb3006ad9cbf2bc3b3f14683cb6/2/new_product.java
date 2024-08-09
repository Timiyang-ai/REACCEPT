public AlluxioURI getPath(long fileId) throws FileDoesNotExistException {
    try (InodePath inodePath = mInodeTree.lockFullInodePath(fileId)) {
      return mInodeTree.getPath(inodePath);
    }
  }