public List<FileBlockInfo> getFileBlockInfoList(AlluxioURI path)
      throws FileDoesNotExistException, InvalidPathException, AccessControlException {
    MasterContext.getMasterSource().incGetFileBlockInfoOps(1);
    try (InodePath inodePath = mInodeTree.getInodePath(path)) {
      mPermissionChecker.checkPermission(FileSystemAction.READ, path);
      Inode<?> inode = inodePath.getInode();
      if (!inode.isFile()) {
        throw new FileDoesNotExistException(ExceptionMessage.PATH_MUST_BE_FILE.getMessage(path));
      }
      List<FileBlockInfo> ret = getFileBlockInfoListInternal((InodeFile) inode);
      MasterContext.getMasterSource().incFileBlockInfosGot(ret.size());
      return ret;
    }
  }