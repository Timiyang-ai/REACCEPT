public void createDirectory(AlluxioURI path, CreateDirectoryOptions options)
      throws InvalidPathException, FileAlreadyExistsException, IOException, AccessControlException,
      FileDoesNotExistException {
    LOG.debug("createDirectory {} ", path);
    MasterContext.getMasterSource().incCreateDirectoriesOps(1);
    long flushCounter = AsyncJournalWriter.INVALID_FLUSH_COUNTER;
    try (LockedInodePath inodePath = mInodeTree.lockInodePath(path, InodeTree.LockMode.WRITE)) {
      mPermissionChecker.checkParentPermission(FileSystemAction.WRITE, inodePath);
      mMountTable.checkUnderWritableMountPoint(path);
      flushCounter = createDirectoryAndJournal(inodePath, options);
    } finally {
      // finally runs after resources are closed (unlocked).
      if (flushCounter != AsyncJournalWriter.INVALID_FLUSH_COUNTER) {
        waitForJournalFlush(flushCounter);
      }
    }
  }