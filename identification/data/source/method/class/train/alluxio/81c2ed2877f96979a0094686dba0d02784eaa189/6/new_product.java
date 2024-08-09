public FileInfo getFileInfo(long fileId) throws FileDoesNotExistException {
    // TODO(gene): metrics
    synchronized (mInodeTree) {
      Inode inode = mInodeTree.getInodeById(fileId);
      return getFileInfo(inode);
    }
  }