public boolean unmount(AlluxioURI alluxioPath)
      throws FileDoesNotExistException, InvalidPathException, IOException, AccessControlException {
    MasterContext.getMasterSource().incUnmountOps(1);
    try (InodePath inodePath = mInodeTree.getInodePath(alluxioPath)) {
      mPermissionChecker.checkParentPermission(FileSystemAction.WRITE, alluxioPath);
      if (unmountAndJournal(alluxioPath)) {
        MasterContext.getMasterSource().incPathsUnmounted(1);
        return true;
      }
      return false;
    }
  }