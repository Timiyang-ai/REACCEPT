public long getNewBlockIdForFile(AlluxioURI path)
      throws FileDoesNotExistException, InvalidPathException, AccessControlException {
    MasterContext.getMasterSource().incGetNewBlockOps(1);
    Inode<?> inode;
    synchronized (mInodeTree) {
      // TODO(bin): write to path not inode, check permission on path
      mPermissionChecker.checkParentPermission(FileSystemAction.WRITE, path);
      inode = mInodeTree.getInodeFileByPath(path);
    }
    MasterContext.getMasterSource().incNewBlocksGot(1);
    return ((InodeFile) inode).getNewBlockId();
  }