public boolean unmount(AlluxioURI alluxioPath)
      throws FileDoesNotExistException, InvalidPathException, IOException, AccessControlException {
    MasterContext.getMasterSource().incUnmountOps(1);
    synchronized (mInodeTree) {
      mPermissionChecker.checkParentPermission(FileSystemAction.WRITE, alluxioPath);
      if (unmountAndJournal(alluxioPath)) {
        MasterContext.getMasterSource().incPathsUnmounted(1);
        return true;
      }
      return false;
    }
  }