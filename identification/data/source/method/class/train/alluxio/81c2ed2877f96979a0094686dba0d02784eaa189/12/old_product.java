public FileInfo getFileInfo(AlluxioURI path)
      throws AccessControlException, FileDoesNotExistException, InvalidPathException {
    MasterContext.getMasterSource().incGetFileInfoOps(1);
    synchronized (mInodeTree) {
      checkPermission(FileSystemAction.READ, path, false);
      // getFileInfo should load from ufs if the file does not exist
      getFileId(path);
      Inode inode = mInodeTree.getInodeByPath(path);
      return getFileInfoInternal(inode);
    }
  }