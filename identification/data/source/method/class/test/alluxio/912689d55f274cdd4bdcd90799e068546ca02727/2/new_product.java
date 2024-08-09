public long getNewBlockIdForFile(AlluxioURI path)
      throws FileDoesNotExistException, InvalidPathException, AccessControlException {
    MasterContext.getMasterSource().incGetNewBlockOps(1);
    try (LockedInodePath inodePath = mInodeTree.lockFullInodePath(path, InodeTree.LockMode.WRITE)) {
      mPermissionChecker.checkPermission(FileSystemAction.WRITE, inodePath);
      MasterContext.getMasterSource().incNewBlocksGot(1);
      return inodePath.getInodeFile().getNewBlockId();
    }
  }