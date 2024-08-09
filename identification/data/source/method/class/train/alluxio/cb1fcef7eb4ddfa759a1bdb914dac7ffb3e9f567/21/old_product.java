private void propagatePersisted(Inode inode) throws FileDoesNotExistException,
      InvalidPathException {
    if (!inode.isPersisted()) {
      return;
    }
    Inode handle = inode;
    while (handle.getParentId() != -1) {
      handle = mInodeTree.getInodeById(handle.getParentId());
      handle.setPersisted(true);
      TachyonURI path = mInodeTree.getPath(handle);
      if (mMountTable.isMountPoint(path)) {
        // Stop propagating the persisted status at mountpoints.
        break;
      }
    }
  }