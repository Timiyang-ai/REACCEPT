public long getFileId(TachyonURI path) {
    synchronized (mInodeTree) {
      Inode inode;
      try {
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