private void propagatePersisted(Inode inode) throws FileDoesNotExistException,
      InvalidPathException {
    if (!inode.isPersisted()) {
      return;
    }
    Inode handle = inode;
    while (handle.getParentId() != InodeTree.NO_PARENT) {
      handle = mInodeTree.getInodeById(handle.getParentId());
      handle.setPersisted(true);
      TachyonURI path = mInodeTree.getPath(handle);
      if (mMountTable.isMountPoint(path)) {
        // Stop propagating the persisted status at mount points.
        break;
      }
    }
  }