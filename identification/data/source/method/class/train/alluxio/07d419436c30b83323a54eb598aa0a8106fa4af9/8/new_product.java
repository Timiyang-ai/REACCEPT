public boolean unmount(AlluxioURI alluxioPath)
      throws FileDoesNotExistException, InvalidPathException, IOException, AccessControlException {
    Metrics.UNMOUNT_OPS_COUNTER.inc();
    long flushCounter = AsyncJournalWriter.INVALID_FLUSH_COUNTER;
    try (
        LockedInodePath inodePath = mInodeTree
            .lockFullInodePath(alluxioPath, InodeTree.LockMode.WRITE)) {
      mPermissionChecker.checkParentPermission(Mode.Bits.WRITE, inodePath);
      flushCounter = unmountAndJournal(inodePath);
      if (flushCounter != AsyncJournalWriter.INVALID_FLUSH_COUNTER) {
        Metrics.PATHS_UNMOUNTED_COUNTER.inc();
        return true;
      }
      return false;
    } finally {
      // finally runs after resources are closed (unlocked).
      waitForJournalFlush(flushCounter);
    }
  }