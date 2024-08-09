public void rename(AlluxioURI srcPath, AlluxioURI dstPath) throws FileAlreadyExistsException,
      FileDoesNotExistException, InvalidPathException, IOException, AccessControlException {
    MasterContext.getMasterSource().incRenamePathOps(1);
    AlluxioURI uriLock1 = srcPath;
    AlluxioURI uriLock2 = dstPath;
    if (srcPath.compareTo(dstPath) > 0) {
      // multiple paths must be locked in deterministic order.
      uriLock1 = dstPath;
      uriLock2 = srcPath;
    }
    // TODO(gpang): lock write parent for src.
    try (InodePath inodePath1 = mInodeTree.lockInodePath(uriLock1, InodeTree.LockMode.WRITE);
         InodePath inodePath2 = mInodeTree.lockInodePath(uriLock2, InodeTree.LockMode.WRITE)) {
      mPermissionChecker.checkParentPermission(FileSystemAction.WRITE, srcPath);
      mPermissionChecker.checkParentPermission(FileSystemAction.WRITE, dstPath);
      mMountTable.checkUnderWritableMountPoint(srcPath);
      mMountTable.checkUnderWritableMountPoint(dstPath);
      InodePath srcInodePath = inodePath1;
      InodePath dstInodePath = inodePath2;
      if (srcPath.compareTo(dstPath) > 0) {
        srcInodePath = inodePath2;
        dstInodePath = inodePath1;
      }
      renameAndJournal(srcInodePath, dstInodePath);
      LOG.debug("Renamed {} to {}", srcPath, dstPath);
    }
  }