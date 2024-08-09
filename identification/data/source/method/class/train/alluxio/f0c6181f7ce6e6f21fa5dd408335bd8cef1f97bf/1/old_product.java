public long getFileId(AlluxioURI path) throws AccessControlException, FileDoesNotExistException {
    synchronized (mInodeTree) {
      Inode<?> inode;
      try {
        mPermissionChecker.checkPermission(FileSystemAction.READ, path);
        if (!mInodeTree.inodePathExists(path)) {
          try {
            return loadMetadata(path, true);
          } catch (Exception e) {
            return IdUtils.INVALID_FILE_ID;
          }
        }
        inode = mInodeTree.getInodeByPath(path);
      } catch (InvalidPathException e) {
        return IdUtils.INVALID_FILE_ID;
      }
      return inode.getId();
    }
  }