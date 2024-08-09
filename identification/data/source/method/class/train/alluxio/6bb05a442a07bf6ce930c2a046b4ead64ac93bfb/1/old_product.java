public List<FileBlockInfo> getFileBlockInfoList(AlluxioURI path)
      throws FileDoesNotExistException, InvalidPathException, AccessControlException {
    mMasterSource.incGetFileBlockInfoOps(1);
    try (LockedInodePath inodePath = mInodeTree.lockFullInodePath(path, InodeTree.LockMode.READ)) {
      mPermissionChecker.checkPermission(Mode.Bits.READ, inodePath);
      List<FileBlockInfo> ret = getFileBlockInfoListInternal(inodePath);
      mMasterSource.incFileBlockInfosGot(ret.size());
      return ret;
    }
  }