private void propagatePersisted(Inode<?> inode, boolean replayed)
      throws FileDoesNotExistException {
    if (!inode.isPersisted()) {
      return;
    }
    Inode<?> handle = inode;
    while (handle.getParentId() != InodeTree.NO_PARENT) {
      handle = mInodeTree.getInodeById(handle.getParentId());
      AlluxioURI path = mInodeTree.getPath(handle);
      if (mMountTable.isMountPoint(path)) {
        // Stop propagating the persisted status at mount points.
        break;
      }
      if (handle.isPersisted()) {
        // Stop if a persisted directory is encountered.
        break;
      }
      handle.setPersistenceState(PersistenceState.PERSISTED);
      if (!replayed) {
        PersistDirectoryEntry persistDirectory = PersistDirectoryEntry.newBuilder()
            .setId(inode.getId())
            .build();
        writeJournalEntry(JournalEntry.newBuilder().setPersistDirectory(persistDirectory).build());
      }
    }
  }