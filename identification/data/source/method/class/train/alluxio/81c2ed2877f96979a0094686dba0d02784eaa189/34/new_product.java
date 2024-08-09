public FileInfo getFileInfo(TachyonURI path)
      throws FileDoesNotExistException, InvalidPathException, AccessControlException {
    MasterContext.getMasterSource().incGetFileInfoOps(1);
    synchronized (mInodeTree) {
      checkPermission(FileSystemAction.READ, path, false);
      Inode inode = mInodeTree.getInodeByPath(path);
      return getFileInfoInternal(inode);
    }
  }