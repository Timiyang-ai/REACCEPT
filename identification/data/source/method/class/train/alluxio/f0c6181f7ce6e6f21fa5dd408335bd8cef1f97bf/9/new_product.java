public long getFileId(AlluxioURI path) throws AccessControlException, FileDoesNotExistException {
    try (InodePath inodePath = mInodeTree.lockInodePath(path)) {
      mPermissionChecker.checkPermission(FileSystemAction.READ, path);
      loadMetadataIfNotExist(path);
      // TODO(gpang): avoid re-locking prefix and continue to traverse from existing InodePath
      try (InodePath fullInodePath = mInodeTree.lockFullInodePath(path)) {
        return fullInodePath.getInode().getId();
      }
    } catch (InvalidPathException | FileDoesNotExistException e) {
      return IdUtils.INVALID_FILE_ID;
    }
  }