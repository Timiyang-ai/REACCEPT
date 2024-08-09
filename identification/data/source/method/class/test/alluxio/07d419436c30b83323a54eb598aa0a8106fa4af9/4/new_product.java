public boolean unmount(AlluxioURI alluxioPath)
      throws FileDoesNotExistException, InvalidPathException, IOException, AccessControlException {
    Metrics.UNMOUNT_OPS.inc();
    long flushCounter = AsyncJournalWriter.INVALID_FLUSH_COUNTER;
    try (
        LockedInodePath inodePath = mInodeTree
            .lockFullInodePath(alluxioPath, InodeTree.LockMode.WRITE_PARENT)) {
      mPermissionChecker.checkParentPermission(Mode.Bits.WRITE, inodePath);
      flushCounter = unmountAndJournal(inodePath);
      if (flushCounter != AsyncJournalWriter.INVALID_FLUSH_COUNTER) {
        Metrics.PATHS_UNMOUNTED.inc();
        return true;
      }
      return false;
    } finally {
      // finally runs after resources are closed (unlocked).
      waitForJournalFlush(flushCounter);
    }
  }