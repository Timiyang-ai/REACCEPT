public boolean unmount(AlluxioURI alluxioPath)
      throws FileDoesNotExistException, InvalidPathException, IOException, AccessControlException {
    mMasterSource.incUnmountOps(1);
    long flushCounter = AsyncJournalWriter.INVALID_FLUSH_COUNTER;
    try (
        LockedInodePath inodePath = mInodeTree
            .lockFullInodePath(alluxioPath, InodeTree.LockMode.WRITE)) {
      mPermissionChecker.checkParentPermission(Mode.Bits.WRITE, inodePath);
      flushCounter = unmountAndJournal(inodePath);
      if (flushCounter != AsyncJournalWriter.INVALID_FLUSH_COUNTER) {
        mMasterSource.incPathsUnmounted(1);
        return true;
      }
      return false;
    } finally {
      // finally runs after resources are closed (unlocked).
      waitForJournalFlush(flushCounter);
    }
  }