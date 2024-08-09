public FileInfo getFileInfo(AlluxioURI path)
      throws FileDoesNotExistException, InvalidPathException, AccessControlException {
    MasterContext.getMasterSource().incGetFileInfoOps(1);
    synchronized (mInodeTree) {
      mPermissionChecker.checkPermission(FileSystemAction.READ, path);
      loadMetadataIfNotExist(path);
      Inode<?> inode = mInodeTree.getInodeByPath(path);
      return getFileInfoInternal(inode);
    }
  }