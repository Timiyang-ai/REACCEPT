public FileInfo getFileInfo(long fileId) throws FileDoesNotExistException, InvalidPathException {
    mMasterSource.incGetFileStatusOps();
    synchronized (mInodeTree) {
      Inode inode = mInodeTree.getInodeById(fileId);
      return getFileInfo(inode);
    }
  }