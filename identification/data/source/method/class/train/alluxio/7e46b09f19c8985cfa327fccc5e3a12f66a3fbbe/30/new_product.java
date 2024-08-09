public void rename(AlluxioURI srcPath, AlluxioURI dstPath) throws FileAlreadyExistsException,
      FileDoesNotExistException, InvalidPathException, IOException, AccessControlException {
    MasterContext.getMasterSource().incRenamePathOps(1);
    try (InodePathPair inodePathPair = mInodeTree
        .lockInodePathPair(srcPath, InodeTree.LockMode.WRITE_PARENT, dstPath,
            InodeTree.LockMode.WRITE)) {
      InodePath srcInodePath = inodePathPair.getFirst();
      InodePath dstInodePath = inodePathPair.getSecond();
      mPermissionChecker.checkParentPermission(FileSystemAction.WRITE, srcPath);
      mPermissionChecker.checkParentPermission(FileSystemAction.WRITE, dstPath);
      mMountTable.checkUnderWritableMountPoint(srcPath);
      mMountTable.checkUnderWritableMountPoint(dstPath);
      renameAndJournal(srcInodePath, dstInodePath);
      LOG.debug("Renamed {} to {}", srcPath, dstPath);
    }
  }