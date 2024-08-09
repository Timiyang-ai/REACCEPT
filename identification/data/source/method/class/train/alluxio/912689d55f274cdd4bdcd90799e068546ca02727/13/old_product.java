public long getNewBlockIdForFile(AlluxioURI path)
      throws FileDoesNotExistException, InvalidPathException, AccessControlException {
    Metrics.GET_NEW_BLOCK_OPS_COUNTER.inc();
    try (LockedInodePath inodePath = mInodeTree.lockFullInodePath(path, InodeTree.LockMode.WRITE)) {
      mPermissionChecker.checkPermission(Mode.Bits.WRITE, inodePath);
      Metrics.NEW_BLOCKS_GOT_COUNTER.inc();
      return inodePath.getInodeFile().getNewBlockId();
    }
  }