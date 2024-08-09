public LockResource lockInode(InodeView inode, LockMode mode) {
    return mInodeLocks.get(inode.getId(), mode);
  }