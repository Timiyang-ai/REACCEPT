public long createFile(AlluxioURI path, CreateFileOptions options)
      throws AccessControlException, InvalidPathException, FileAlreadyExistsException,
          BlockInfoException, IOException, FileDoesNotExistException {
    MasterContext.getMasterSource().incCreateFileOps(1);
    long flushCounter = AsyncJournalWriter.INVALID_FLUSH_COUNTER;
    try (LockedInodePath inodePath = mInodeTree.lockInodePath(path, InodeTree.LockMode.WRITE)) {
      mPermissionChecker.checkParentPermission(FileSystemAction.WRITE, inodePath);
      mMountTable.checkUnderWritableMountPoint(path);
      flushCounter = createFileAndJournal(inodePath, options);
      return inodePath.getInode().getId();
    } finally {
      // finally runs after resources are closed (unlocked).
      if (flushCounter != AsyncJournalWriter.INVALID_FLUSH_COUNTER) {
        waitForJournalFlush(flushCounter);
      }
    }
  }