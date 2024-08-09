public void rename(AlluxioURI srcPath, AlluxioURI dstPath) throws FileAlreadyExistsException,
      FileDoesNotExistException, InvalidPathException, IOException, AccessControlException {
    MasterContext.getMasterSource().incRenamePathOps(1);
    long flushCounter = AsyncJournalWriter.INVALID_FLUSH_COUNTER;
    // Both src and dst paths should lock WRITE_PARENT, to modify the parent inodes for both paths.
    try (InodePathPair inodePathPair = mInodeTree
        .lockInodePathPair(srcPath, InodeTree.LockMode.WRITE_PARENT, dstPath,
            InodeTree.LockMode.WRITE_PARENT)) {
      LockedInodePath srcInodePath = inodePathPair.getFirst();
      LockedInodePath dstInodePath = inodePathPair.getSecond();
      mPermissionChecker.checkParentPermission(FileSystemAction.WRITE, srcInodePath);
      mPermissionChecker.checkParentPermission(FileSystemAction.WRITE, dstInodePath);
      mMountTable.checkUnderWritableMountPoint(srcPath);
      mMountTable.checkUnderWritableMountPoint(dstPath);
      flushCounter = renameAndJournal(srcInodePath, dstInodePath);
      LOG.debug("Renamed {} to {}", srcPath, dstPath);
    } finally {
      // finally runs after resources are closed (unlocked).
      waitForJournalFlush(flushCounter);
    }
  }