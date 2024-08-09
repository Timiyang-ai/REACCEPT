private void propagatePersisted(Inode inode, boolean replayed) throws FileDoesNotExistException,
      InvalidPathException {
    if (!inode.isPersisted()) {
      return;
    }
    Inode handle = inode;
    while (handle.getParentId() != InodeTree.NO_PARENT) {
      handle = mInodeTree.getInodeById(handle.getParentId());
      if (handle.isPersisted()) {
        // Stop if a persisted directory is encountered.
        break;
      }
      handle.setPersisted(true);
      if (!replayed) {
        writeJournalEntry(new PersistDirectoryEntry(inode.getId(), inode.isPersisted()));
      }
      TachyonURI path = mInodeTree.getPath(handle);
      if (mMountTable.isMountPoint(path)) {
        // Stop propagating the persisted status at mount points.
        break;
      }
    }
  }