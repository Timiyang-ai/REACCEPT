public void mount(AlluxioURI alluxioPath, AlluxioURI ufsPath, MountOptions options)
      throws FileAlreadyExistsException, InvalidPathException, IOException, AccessControlException {
    MasterContext.getMasterSource().incMountOps(1);
    try (InodePath inodePath = mInodeTree.lockInodePath(alluxioPath)) {
      mPermissionChecker.checkParentPermission(FileSystemAction.WRITE, alluxioPath);
      mMountTable.checkUnderWritableMountPoint(alluxioPath);
      mountAndJournal(alluxioPath, ufsPath, options);
      MasterContext.getMasterSource().incPathsMounted(1);
    }
  }