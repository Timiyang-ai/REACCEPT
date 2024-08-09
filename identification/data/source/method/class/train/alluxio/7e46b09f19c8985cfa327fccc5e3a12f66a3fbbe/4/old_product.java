public void rename(AlluxioURI srcPath, AlluxioURI dstPath) throws FileAlreadyExistsException,
      FileDoesNotExistException, InvalidPathException, IOException, AccessControlException {
    Metrics.RENAME_PATH_OPS.inc();
    long flushCounter = AsyncJournalWriter.INVALID_FLUSH_COUNTER;
    // Both src and dst paths use WRITE locks, despite possibly updating the parent inodes. The
    // modify operations on the parent inodes are thread safe.
    try (InodePathPair inodePathPair =
        mInodeTree.lockInodePathPair(srcPath, InodeTree.LockMode.WRITE, dstPath,
            InodeTree.LockMode.WRITE)) {
      LockedInodePath srcInodePath = inodePathPair.getFirst();
      LockedInodePath dstInodePath = inodePathPair.getSecond();
      mPermissionChecker.checkParentPermission(Mode.Bits.WRITE, srcInodePath);
      mPermissionChecker.checkParentPermission(Mode.Bits.WRITE, dstInodePath);
      mMountTable.checkUnderWritableMountPoint(srcPath);
      mMountTable.checkUnderWritableMountPoint(dstPath);
      flushCounter = renameAndJournal(srcInodePath, dstInodePath);
      LOG.debug("Renamed {} to {}", srcPath, dstPath);
    } finally {
      // finally runs after resources are closed (unlocked).
      waitForJournalFlush(flushCounter);
    }
  }