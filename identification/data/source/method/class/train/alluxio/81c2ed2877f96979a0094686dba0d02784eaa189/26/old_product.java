public FileInfo getFileInfo(long fileId) throws FileDoesNotExistException {
    MasterContext.getMasterSource().incGetFileStatusOps();
    synchronized (mInodeTree) {
      Inode inode = mInodeTree.getInodeById(fileId);
      return getFileInfo(inode);
    }
  }