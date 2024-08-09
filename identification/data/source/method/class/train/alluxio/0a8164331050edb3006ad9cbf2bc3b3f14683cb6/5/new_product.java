public AlluxioURI getPath(long fileId) throws FileDoesNotExistException {
    try (InodePath inodePath = mInodeTree.getInodePath(fileId)) {
      return mInodeTree.getPath(inodePath);
    }
  }