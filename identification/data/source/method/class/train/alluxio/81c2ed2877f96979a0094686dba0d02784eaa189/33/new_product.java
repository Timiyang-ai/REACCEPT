public FileInfo getFileInfo(long fileId) throws FileDoesNotExistException {
    Metrics.GET_FILE_INFO_OPS_COUNTER.inc();
    try (
        LockedInodePath inodePath = mInodeTree.lockFullInodePath(fileId, InodeTree.LockMode.READ)) {
      return getFileInfoInternal(inodePath);
    }
  }