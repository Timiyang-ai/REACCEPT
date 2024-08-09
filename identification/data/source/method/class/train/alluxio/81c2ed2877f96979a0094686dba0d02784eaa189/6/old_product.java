public FileInfo getFileInfo(long fileId) throws FileDoesNotExistException, InvalidPathException {
    // TODO(gene): metrics
    synchronized (mInodeTree) {
      Inode inode = mInodeTree.getInodeById(fileId);
      return getFileInfo(inode);
    }
  }