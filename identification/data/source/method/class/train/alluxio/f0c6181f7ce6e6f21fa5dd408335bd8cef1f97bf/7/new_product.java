public long getFileId(AlluxioURI path) throws AccessControlException {
    synchronized (mInodeTree) {
      Inode inode;
      checkPermission(FileSystemAction.READ, path, false);
      if (!mInodeTree.inodePathExists(path)) {
        try {
          return loadMetadata(path, true);
        } catch (Exception e) {
          return IdUtils.INVALID_FILE_ID;
        }
      }
      try {
        inode = mInodeTree.getInodeByPath(path);
      } catch (InvalidPathException e) {
        return IdUtils.INVALID_FILE_ID;
      }
      return inode.getId();
    }
  }