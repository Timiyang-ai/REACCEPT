public FileInfo getFileInfo(TachyonURI uri)
      throws FileDoesNotExistException, InvalidPathException {
    MasterContext.getMasterSource().incGetFileInfoOps(1);
    synchronized (mInodeTree) {
      Inode inode = mInodeTree.getInodeByPath(uri);
      return getFileInfoInternal(inode);
    }
  }