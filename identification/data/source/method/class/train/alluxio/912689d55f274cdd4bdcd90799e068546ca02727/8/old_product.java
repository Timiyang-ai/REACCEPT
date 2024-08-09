public long getNewBlockIdForFile(long fileId) throws FileDoesNotExistException {
    MasterContext.getMasterSource().incGetNewBlockOps(1);
    Inode inode;
    synchronized (mInodeTree) {
      inode = mInodeTree.getInodeById(fileId);
    }
    if (!inode.isFile()) {
      throw new FileDoesNotExistException(ExceptionMessage.FILEID_MUST_BE_FILE.getMessage(fileId));
    }
    MasterContext.getMasterSource().incNewBlocksGot(1);
    return ((InodeFile) inode).getNewBlockId();
  }