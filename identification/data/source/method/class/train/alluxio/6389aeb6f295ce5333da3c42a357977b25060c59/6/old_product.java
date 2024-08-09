public void setPinned(Inode inode, boolean pinned) {
    inode.setPinned(pinned);
    inode.setLastModificationTimeMs(System.currentTimeMillis());

    if (inode.isFile()) {
      if (inode.isPinned()) {
        mPinnedInodeFileIds.add(inode.getId());
      } else {
        mPinnedInodeFileIds.remove(inode.getId());
      }
    } else {
      // inode is a directory. Set the pinned state for all children.
      for (Inode child : ((InodeDirectory) inode).getChildren()) {
        setPinned(child, pinned);
      }
    }
  }