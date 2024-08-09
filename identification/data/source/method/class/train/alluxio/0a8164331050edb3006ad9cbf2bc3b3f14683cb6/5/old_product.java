public AlluxioURI getPath(long fileId) throws FileDoesNotExistException {
    synchronized (mInodeTree) {
      return mInodeTree.getPath(mInodeTree.getInodeById(fileId));
    }
  }