@Deprecated // Currently used by block worker and web UI
  public FileInfo getFileInfo(long fileId) throws FileDoesNotExistException {
    MasterContext.getMasterSource().incGetFileInfoOps(1);
    synchronized (mInodeTree) {
      Inode<?> inode = mInodeTree.getInodeById(fileId);
      return getFileInfoInternal(inode);
    }
  }