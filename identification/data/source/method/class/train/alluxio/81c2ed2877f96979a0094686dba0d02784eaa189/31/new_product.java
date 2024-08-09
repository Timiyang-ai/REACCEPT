public FileInfo getFileInfo(AlluxioURI path)
      throws FileDoesNotExistException, InvalidPathException, AccessControlException {
    Metrics.GET_FILE_INFO_OPS.inc();
    Mutable<Long> flushCounter = new MutableObject<>(AsyncJournalWriter.INVALID_FLUSH_COUNTER);

    // Get a READ lock first to see if we need to load metadata, note that this assumes load
    // metadata for direct children is disabled by default.
    try (LockedInodePath inodePath = mInodeTree.lockInodePath(path, InodeTree.LockMode.READ)) {
      mPermissionChecker.checkPermission(Mode.Bits.READ, inodePath);
      if (inodePath.fullPathExists()) {
        // The file already exists, so metadata does not need to be loaded.
        return getFileInfoInternal(inodePath);
      }
    }

    try (LockedInodePath inodePath = mInodeTree.lockInodePath(path, InodeTree.LockMode.WRITE)) {
      // This is WRITE locked, since loading metadata is possible.
      mPermissionChecker.checkPermission(Mode.Bits.READ, inodePath);
      loadMetadataIfNotExistAndJournal(inodePath,
          LoadMetadataOptions.defaults().setCreateAncestors(true), flushCounter);
      mInodeTree.ensureFullInodePath(inodePath, InodeTree.LockMode.READ);
      return getFileInfoInternal(inodePath);
    } finally {
      // finally runs after resources are closed (unlocked).
      waitForJournalFlush(flushCounter.getValue());
    }
  }