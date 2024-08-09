public void mount(AlluxioURI alluxioPath, AlluxioURI ufsPath, MountOptions options)
      throws FileAlreadyExistsException, InvalidPathException, IOException, AccessControlException {
    MasterContext.getMasterSource().incMountOps(1);
    long flushCounter = AsyncJournalWriter.INVALID_FLUSH_COUNTER;
    try (InodePath inodePath = mInodeTree.lockInodePath(alluxioPath, InodeTree.LockMode.WRITE)) {
      mPermissionChecker.checkParentPermission(FileSystemAction.WRITE, inodePath);
      mMountTable.checkUnderWritableMountPoint(alluxioPath);
      flushCounter = mountAndJournal(inodePath, ufsPath, options);
      MasterContext.getMasterSource().incPathsMounted(1);
    } finally {
      // finally runs after resources are closed (unlocked).
      if (flushCounter != AsyncJournalWriter.INVALID_FLUSH_COUNTER) {
        waitForJournalFlush(flushCounter);
      }
    }
  }