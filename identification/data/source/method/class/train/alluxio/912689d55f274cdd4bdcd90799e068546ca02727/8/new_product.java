public long getNewBlockIdForFile(TachyonURI uri)
      throws FileDoesNotExistException, InvalidPathException {
    MasterContext.getMasterSource().incGetNewBlockOps(1);
    Inode inode;
    synchronized (mInodeTree) {
      inode = mInodeTree.getInodeByPath(uri);
    }
    if (!inode.isFile()) {
      throw new FileDoesNotExistException(ExceptionMessage.PATH_MUST_BE_FILE.getMessage(uri));
    }
    MasterContext.getMasterSource().incNewBlocksGot(1);
    return ((InodeFile) inode).getNewBlockId();
  }