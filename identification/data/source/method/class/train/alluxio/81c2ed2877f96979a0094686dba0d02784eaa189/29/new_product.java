public FileInfo getFileInfo(AlluxioURI path)
      throws FileDoesNotExistException, InvalidPathException, AccessControlException {
    MasterContext.getMasterSource().incGetFileInfoOps(1);
    try (InodePath inodePath = mInodeTree.getInodePath(path)) {
      mPermissionChecker.checkPermission(FileSystemAction.READ, path);
      loadMetadataIfNotExist(path);
      // TODO(gpang): avoid re-locking prefix and continue to traverse from existing InodePath
      try (InodePath fullInodePath = mInodeTree.getInodePath(path)) {
        return getFileInfoInternal(fullInodePath.getInode());
      }
    }
  }