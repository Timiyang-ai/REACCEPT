public long getNewBlockIdForFile(AlluxioURI path)
      throws FileDoesNotExistException, InvalidPathException {
    MasterContext.getMasterSource().incGetNewBlockOps(1);
    Inode inode;
    synchronized (mInodeTree) {
      inode = mInodeTree.getInodeByPath(path);
    }
    if (!inode.isFile()) {
      throw new FileDoesNotExistException(ExceptionMessage.PATH_MUST_BE_FILE.getMessage(path));
    }
    MasterContext.getMasterSource().incNewBlocksGot(1);
    return ((InodeFile) inode).getNewBlockId();
  }