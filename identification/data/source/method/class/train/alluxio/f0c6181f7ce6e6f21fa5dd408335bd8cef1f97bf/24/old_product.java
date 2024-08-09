public long getFileId(TachyonURI path) {
    // TODO(manugoyal) make sure client and master agree that -1 is an invalid file id
    synchronized (mInodeTree) {
      Inode inode;
      try {
        inode = mInodeTree.getInodeByPath(path);
      } catch (InvalidPathException e) {
        return -1;
      }
      return inode.getId();
    }
  }