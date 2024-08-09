public long getFileId(TachyonURI path) throws InvalidPathException {
    synchronized (mInodeTree) {
      try {
        Inode inode = mInodeTree.getInodeByPath(path);
        return inode.getId();
      } catch (InvalidPathException e) {
        try {
          return loadMetadata(path, true);
        } catch (Exception e2) {
          throw e;
        }
      }
    }
  }