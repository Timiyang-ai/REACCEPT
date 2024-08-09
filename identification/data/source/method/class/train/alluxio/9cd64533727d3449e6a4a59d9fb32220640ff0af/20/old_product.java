public void setAttribute(AlluxioURI path, SetAttributeOptions options)
      throws FileDoesNotExistException, AccessControlException, InvalidPathException {
    MasterContext.getMasterSource().incSetAttributeOps(1);
    // for chown
    boolean rootRequired = options.getOwner() != null;
    // for chgrp, chmod
    boolean ownerRequired =
        (options.getGroup() != null) || (options.getPermission() != Constants.INVALID_MODE);
    long flushCounter = AsyncJournalWriter.INVALID_FLUSH_COUNTER;
    try (LockedInodePath inodePath = mInodeTree.lockFullInodePath(path, InodeTree.LockMode.WRITE)) {
      mPermissionChecker.checkSetAttributePermission(inodePath, rootRequired, ownerRequired);
      flushCounter = setAttributeAndJournal(inodePath, options, rootRequired, ownerRequired);
    } finally {
      // finally runs after resources are closed (unlocked).
      waitForJournalFlush(flushCounter);
    }
  }