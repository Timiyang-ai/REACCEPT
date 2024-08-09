public void deleteInode(Inode inode) {
    InodeDirectory parent = (InodeDirectory) getInodeById(inode.getParentId());
    parent.removeChild(inode);
    parent.setLastModificationTimeMs(System.currentTimeMillis());

    mInodeIdToInodes.remove(inode.getId());
    mPinnedInodeFileIds.remove(inode.getId());
    inode.reverseId();
  }