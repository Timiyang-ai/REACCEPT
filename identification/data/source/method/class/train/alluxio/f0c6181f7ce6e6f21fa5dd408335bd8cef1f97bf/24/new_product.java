public long getFileId(TachyonURI path) {
    synchronized (mInodeTree) {
      Inode inode;
      try {
        inode = mInodeTree.getInodeByPath(path);
      } catch (InvalidPathException e) {
        return IdUtils.INVALID_FILE_ID;
      }
      return inode.getId();
    }
  }