public FileInfo getFileInfo(AlluxioURI path)
      throws FileDoesNotExistException, InvalidPathException, AccessControlException {
    MasterContext.getMasterSource().incGetFileInfoOps(1);
    long flushCounter = AsyncJournalWriter.INVALID_FLUSH_COUNTER;
    try (InodePath inodePath = mInodeTree.lockInodePath(path, InodeTree.LockMode.READ)) {
      mPermissionChecker.checkPermission(FileSystemAction.READ, inodePath);
      flushCounter = loadMetadataIfNotExistAndJournal(inodePath);
      mInodeTree.ensureFullInodePath(inodePath, InodeTree.LockMode.READ);
      return getFileInfoInternal(inodePath);
    } finally {
      // finally runs after resources are closed (unlocked).
      if (flushCounter != AsyncJournalWriter.INVALID_FLUSH_COUNTER) {
        waitForJournalFlush(flushCounter);
      }
    }
  }