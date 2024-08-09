public FileInfo getFileInfo(long fileId) throws FileDoesNotExistException {
    mMasterSource.incGetFileInfoOps(1);
    try (
        LockedInodePath inodePath = mInodeTree.lockFullInodePath(fileId, InodeTree.LockMode.READ)) {
      return getFileInfoInternal(inodePath);
    }
  }