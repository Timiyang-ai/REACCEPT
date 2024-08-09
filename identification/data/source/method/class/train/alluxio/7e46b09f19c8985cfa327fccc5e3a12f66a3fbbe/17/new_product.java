public boolean free(AlluxioURI path, boolean recursive)
      throws FileDoesNotExistException, InvalidPathException, AccessControlException {
    Metrics.FREE_FILE_OPS.inc();
    try (LockedInodePath inodePath = mInodeTree.lockFullInodePath(path, InodeTree.LockMode.READ)) {
      mPermissionChecker.checkPermission(Mode.Bits.READ, inodePath);
      return freeInternal(inodePath, recursive);
    }
  }