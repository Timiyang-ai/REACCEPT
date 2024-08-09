public void mount(AlluxioURI alluxioPath, AlluxioURI ufsPath, MountOptions options)
      throws FileAlreadyExistsException, FileDoesNotExistException, InvalidPathException,
      IOException, AccessControlException {
    Metrics.MOUNT_OPS_COUNTER.inc();
    long flushCounter = AsyncJournalWriter.INVALID_FLUSH_COUNTER;
    try (LockedInodePath inodePath = mInodeTree
        .lockInodePath(alluxioPath, InodeTree.LockMode.WRITE)) {
      mPermissionChecker.checkParentPermission(Mode.Bits.WRITE, inodePath);
      mMountTable.checkUnderWritableMountPoint(alluxioPath);
      flushCounter = mountAndJournal(inodePath, ufsPath, options);
      Metrics.PATHS_MOUNTED_COUNTER.inc();
    } finally {
      // finally runs after resources are closed (unlocked).
      waitForJournalFlush(flushCounter);
    }
  }