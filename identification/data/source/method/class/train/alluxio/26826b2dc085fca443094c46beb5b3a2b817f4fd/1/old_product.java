public LockResource lockInode(InodeView inode, LockMode mode) {
    return lock(mInodeLocks.getUnchecked(inode.getId()), mode);
  }