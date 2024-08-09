public TachyonURI getPath(long fileId) throws FileDoesNotExistException {
    return mInodeTree.getPath(mInodeTree.getInodeById(fileId));
  }