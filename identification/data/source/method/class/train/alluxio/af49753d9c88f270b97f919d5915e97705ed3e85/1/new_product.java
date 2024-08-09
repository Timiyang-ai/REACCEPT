public void rename(AlluxioURI srcPath, AlluxioURI dstPath, RenameOptions options)
      throws FileAlreadyExistsException, FileDoesNotExistException, InvalidPathException,
      IOException, AccessControlException {
    Metrics.RENAME_PATH_OPS.inc();
    Mutable<Long> flushCounter = new MutableObject<>(AsyncJournalWriter.INVALID_FLUSH_COUNTER);
    // Require a WRITE lock on the source but only a READ lock on the destination. Since the
    // destination should not exist, we will only obtain a READ lock on the destination parent. The
    // modify operations on the parent inodes are thread safe so WRITE locks are not required.
    try (InodePathPair inodePathPair =
        mInodeTree.lockInodePathPair(srcPath, InodeTree.LockMode.WRITE, dstPath,
            InodeTree.LockMode.READ)) {
      LockedInodePath srcInodePath = inodePathPair.getFirst();
      LockedInodePath dstInodePath = inodePathPair.getSecond();
      mPermissionChecker.checkParentPermission(Mode.Bits.WRITE, srcInodePath);
      mPermissionChecker.checkParentPermission(Mode.Bits.WRITE, dstInodePath);
      mMountTable.checkUnderWritableMountPoint(srcPath);
      mMountTable.checkUnderWritableMountPoint(dstPath);
      renameAndJournal(srcInodePath, dstInodePath, flushCounter, options);
      LOG.debug("Renamed {} to {}", srcPath, dstPath);
    } finally {
      // finally runs after resources are closed (unlocked).
      waitForJournalFlush(flushCounter.getValue());
    }
  }