public long getFileId(AlluxioURI path) throws AccessControlException, FileDoesNotExistException {
    synchronized (mInodeTree) {
      Inode<?> inode;
      try {
        mPermissionChecker.checkPermission(FileSystemAction.READ, path);
        loadMetadataIfNotExist(path);
        inode = mInodeTree.getInodeByPath(path);
      } catch (InvalidPathException | FileDoesNotExistException e) {
        return IdUtils.INVALID_FILE_ID;
      }
      return inode.getId();
    }
  }