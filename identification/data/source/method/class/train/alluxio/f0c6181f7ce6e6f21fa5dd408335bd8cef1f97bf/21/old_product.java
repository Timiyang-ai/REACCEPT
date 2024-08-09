public long getFileId(AlluxioURI path) throws AccessControlException {
    synchronized (mInodeTree) {
      Inode inode;
      try {
        checkPermission(FileSystemAction.READ, path, false);
        inode = mInodeTree.getInodeByPath(path);
      } catch (InvalidPathException e) {
        try {
          return loadMetadata(path, true);
        } catch (Exception e2) {
          return IdUtils.INVALID_FILE_ID;
        }
      }
      return inode.getId();
    }
  }