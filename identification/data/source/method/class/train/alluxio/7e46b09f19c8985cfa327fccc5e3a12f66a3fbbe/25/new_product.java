public boolean free(AlluxioURI path, boolean recursive)
      throws FileDoesNotExistException, InvalidPathException, AccessControlException {
    MasterContext.getMasterSource().incFreeFileOps(1);
    try (LockedInodePath inodePath = mInodeTree.lockFullInodePath(path, InodeTree.LockMode.READ)) {
      mPermissionChecker.checkPermission(FileSystemAction.READ, inodePath);
      return freeInternal(inodePath, recursive);
    }
  }