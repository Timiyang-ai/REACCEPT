public long getNewBlockIdForFile(long fileId) throws FileDoesNotExistException {
    MasterContext.getMasterSource().incNewBlockRequestOps(1);
    Inode inode;
    synchronized (mInodeTree) {
      inode = mInodeTree.getInodeById(fileId);
    }
    if (!inode.isFile()) {
      throw new FileDoesNotExistException(ExceptionMessage.FILEID_MUST_BE_FILE.getMessage(fileId));
    }
    long newBlock = ((InodeFile) inode).getNewBlockId();
    MasterContext.getMasterSource().incNewBlocksRequested(1);
    return newBlock;
  }