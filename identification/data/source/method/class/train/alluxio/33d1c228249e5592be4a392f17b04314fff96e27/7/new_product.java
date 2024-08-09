public void mount(AlluxioURI alluxioPath, AlluxioURI ufsPath, MountOptions options)
      throws FileAlreadyExistsException, InvalidPathException, IOException, AccessControlException {
    MasterContext.getMasterSource().incMountOps(1);
    try (InodePath inodePath = mInodeTree.lockInodePath(alluxioPath, InodeTree.LockMode.WRITE)) {
      mPermissionChecker.checkParentPermission(FileSystemAction.WRITE, alluxioPath);
      mMountTable.checkUnderWritableMountPoint(alluxioPath);
      mountAndJournal(inodePath, ufsPath, options);
      MasterContext.getMasterSource().incPathsMounted(1);
    }
  }