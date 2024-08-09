public void rename(AlluxioURI srcPath, AlluxioURI dstPath) throws FileAlreadyExistsException,
      FileDoesNotExistException, InvalidPathException, IOException, AccessControlException {
    MasterContext.getMasterSource().incRenamePathOps(1);
    AlluxioURI uriLock1 = srcPath;
    AlluxioURI uriLock2 = dstPath;
    if (srcPath.compareTo(dstPath) > 0) {
      uriLock1 = dstPath;
      uriLock2 = srcPath;
    }
    try (InodePath inodePath1 = mInodeTree.lockInodePath(uriLock1);
         InodePath inodePath2 = mInodeTree.lockInodePath(uriLock2)) {
      mPermissionChecker.checkParentPermission(FileSystemAction.WRITE, srcPath);
      mPermissionChecker.checkParentPermission(FileSystemAction.WRITE, dstPath);
      mMountTable.checkUnderWritableMountPoint(srcPath);
      mMountTable.checkUnderWritableMountPoint(dstPath);
      renameAndJournal(srcPath, dstPath);
      LOG.debug("Renamed {} to {}", srcPath, dstPath);
    }
  }