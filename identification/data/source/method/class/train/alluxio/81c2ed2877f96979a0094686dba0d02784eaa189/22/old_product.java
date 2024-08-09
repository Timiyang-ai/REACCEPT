public FileInfo getFileInfo(AlluxioURI path)
      throws FileDoesNotExistException, InvalidPathException, AccessControlException {
    MasterContext.getMasterSource().incGetFileInfoOps(1);
    synchronized (mInodeTree) {
      mPermissionChecker.checkPermission(FileSystemAction.READ, path);
      // getFileInfo should load from ufs if the file does not exist
      getFileId(path);
      Inode<?> inode = mInodeTree.getInodeByPath(path);
      return getFileInfoInternal(inode);
    }
  }