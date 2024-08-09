public FileInfo getFileInfo(long fileId) throws FileDoesNotExistException {
    MasterContext.getMasterSource().incGetFileInfoOps(1);
    try (InodePath inodePath = mInodeTree.getInodePath(fileId)) {
      return getFileInfoInternal(inodePath.getInode());
    }
  }