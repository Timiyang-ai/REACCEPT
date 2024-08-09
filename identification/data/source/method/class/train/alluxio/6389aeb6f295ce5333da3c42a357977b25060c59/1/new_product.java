public void deleteInode(Inode inode) {
    InodeDirectory parent = (InodeDirectory) getInodeById(inode.getParentId());
    parent.removeChild(inode);
    parent.setLastModificationTimeMs(System.currentTimeMillis());

    mInodes.remove(inode);
    mPinnedInodeFileIds.remove(inode.getId());
    inode.reverseId();
  }