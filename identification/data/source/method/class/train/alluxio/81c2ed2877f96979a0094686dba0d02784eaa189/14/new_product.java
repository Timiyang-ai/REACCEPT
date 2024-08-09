public FileInfo getFileInfo(AlluxioURI path)
      throws FileDoesNotExistException, InvalidPathException, AccessControlException {
    MasterContext.getMasterSource().incGetFileInfoOps(1);
    try (InodePath inodePath = mInodeTree.lockInodePath(path, InodeTree.LockMode.READ)) {
      mPermissionChecker.checkPermission(FileSystemAction.READ, inodePath);
      loadMetadataIfNotExist(inodePath);
      mInodeTree.ensureFullInodePath(inodePath, InodeTree.LockMode.READ);
      return getFileInfoInternal(inodePath);
    }
  }