public List<FileBlockInfo> getFileBlockInfoList(AlluxioURI path)
      throws FileDoesNotExistException, InvalidPathException, AccessControlException {
    MasterContext.getMasterSource().incGetFileBlockInfoOps(1);
    try (InodePath inodePath = mInodeTree.lockFullInodePath(path, InodeTree.LockMode.READ)) {
      mPermissionChecker.checkPermission(FileSystemAction.READ, path);
      InodeFile inode = inodePath.getInodeFile();
      List<FileBlockInfo> ret = getFileBlockInfoListInternal(inode);
      MasterContext.getMasterSource().incFileBlockInfosGot(ret.size());
      return ret;
    }
  }