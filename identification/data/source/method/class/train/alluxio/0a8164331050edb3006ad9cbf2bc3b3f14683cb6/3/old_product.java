public TachyonURI getPath(int fileId) throws FileDoesNotExistException {
    return mInodeTree.getPath(mInodeTree.getInodeById(fileId));
  }