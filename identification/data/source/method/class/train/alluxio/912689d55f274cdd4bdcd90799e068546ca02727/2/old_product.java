public long getNewBlockIdForFile(long fileId) throws FileDoesNotExistException {
    Inode inode;
    synchronized (mInodeTree) {
      inode = mInodeTree.getInodeById(fileId);
    }
    if (!inode.isFile()) {
      throw new FileDoesNotExistException(ExceptionMessage.FILEID_MUST_BE_FILE.getMessage(fileId));
    }

    return ((InodeFile) inode).getNewBlockId();
  }