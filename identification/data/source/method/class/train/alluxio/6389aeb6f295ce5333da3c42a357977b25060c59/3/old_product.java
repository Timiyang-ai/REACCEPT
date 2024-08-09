public void setPinned(Inode inode, boolean pinned) {
    inode.setPinned(pinned);
    inode.setLastModificationTimeMs(System.currentTimeMillis());

    if (inode.isDirectory()) {
      // inode is a directory. Set the pinned state for all children.
      for (Inode child : ((InodeDirectory) inode).getChildren()) {
        setPinned(child, pinned);
      }
    }
  }