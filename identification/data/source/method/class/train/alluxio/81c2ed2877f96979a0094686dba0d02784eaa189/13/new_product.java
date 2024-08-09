public FileInfo getFileInfo(TachyonURI path)
      throws FileDoesNotExistException, InvalidPathException {
    MasterContext.getMasterSource().incGetFileInfoOps(1);
    synchronized (mInodeTree) {
      Inode inode = mInodeTree.getInodeByPath(path);
      return getFileInfoInternal(inode);
    }
  }