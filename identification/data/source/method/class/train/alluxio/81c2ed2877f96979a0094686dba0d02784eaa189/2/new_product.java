public FileInfo getFileInfo(long fileId) throws FileDoesNotExistException, InvalidPathException {
    MasterContext.getMasterSource().incGetFileStatusOps();
    synchronized (mInodeTree) {
      Inode inode = mInodeTree.getInodeById(fileId);
      return getFileInfo(inode);
    }
  }