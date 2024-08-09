public long getNewBlockIdForFile(AlluxioURI path)
      throws FileDoesNotExistException, InvalidPathException, AccessControlException {
    MasterContext.getMasterSource().incGetNewBlockOps(1);
    try (InodePath inodePath = mInodeTree.lockFullInodePath(path, InodeTree.LockMode.WRITE)) {
      mPermissionChecker.checkPermission(FileSystemAction.WRITE, path);
      MasterContext.getMasterSource().incNewBlocksGot(1);
      return inodePath.getInodeFile().getNewBlockId();
    }
  }