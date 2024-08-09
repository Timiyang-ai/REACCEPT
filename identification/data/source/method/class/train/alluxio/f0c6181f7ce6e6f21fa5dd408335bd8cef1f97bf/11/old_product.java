public long getFileId(AlluxioURI path) throws AccessControlException, FileDoesNotExistException {
    try (InodePath inodePath = mInodeTree.lockInodePath(path, InodeTree.LockMode.READ)) {
      mPermissionChecker.checkPermission(FileSystemAction.READ, path);
      loadMetadataIfNotExist(path);
      // TODO(gpang): avoid re-locking prefix and continue to traverse from existing InodePath
      try (InodePath fullInodePath = mInodeTree.lockFullInodePath(path, InodeTree.LockMode.READ)) {
        return fullInodePath.getInode().getId();
      }
    } catch (InvalidPathException | FileDoesNotExistException e) {
      return IdUtils.INVALID_FILE_ID;
    }
  }