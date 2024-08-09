public long getNewBlockIdForFile(AlluxioURI path)
      throws FileDoesNotExistException, InvalidPathException, AccessControlException {
    mMasterSource.incGetNewBlockOps(1);
    try (LockedInodePath inodePath = mInodeTree.lockFullInodePath(path, InodeTree.LockMode.WRITE)) {
      mPermissionChecker.checkPermission(Mode.Bits.WRITE, inodePath);
      mMasterSource.incNewBlocksGot(1);
      return inodePath.getInodeFile().getNewBlockId();
    }
  }