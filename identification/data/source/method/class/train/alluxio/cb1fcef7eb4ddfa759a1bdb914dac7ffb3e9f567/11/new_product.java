@GuardedBy("mInodeTree")
  private List<Inode<?>> propagatePersistedInternal(InodePath inodePath, boolean replayed)
      throws FileDoesNotExistException {
    Inode<?> inode = inodePath.getInode();
    if (!inode.isPersisted()) {
      return Collections.emptyList();
    }

    List<Inode<?>> inodes = inodePath.getInodeList();
    // Traverse the inodes from target inode to the root.
    Collections.reverse(inodes);
    // Skip the first, to not examine the target inode itself.
    inodes = inodes.subList(1, inodes.size());

    List<Inode<?>> persistedInodes = new ArrayList<>();
    for (Inode<?> handle : inodes) {
      // the path is already locked.
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
        persistedInodes.add(inode);
      }
    }
    return persistedInodes;
  }