public void mount(AlluxioURI alluxioPath, AlluxioURI ufsPath, MountOptions options)
      throws FileAlreadyExistsException, InvalidPathException, IOException, AccessControlException {
    MasterContext.getMasterSource().incMountOps(1);
    synchronized (mInodeTree) {
      mPermissionChecker.checkParentPermission(FileSystemAction.WRITE, alluxioPath);
      mMountTable.checkUnderWritableMountPoint(alluxioPath);
      mountAndJournal(alluxioPath, ufsPath, options);
      MasterContext.getMasterSource().incPathsMounted(1);
    }
  }