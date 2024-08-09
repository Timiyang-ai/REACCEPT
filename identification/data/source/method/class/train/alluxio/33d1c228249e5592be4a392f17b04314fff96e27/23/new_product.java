public void mount(AlluxioURI alluxioPath, AlluxioURI ufsPath)
      throws FileAlreadyExistsException, InvalidPathException, IOException, AccessControlException {
    MasterContext.getMasterSource().incMountOps(1);
    synchronized (mInodeTree) {
      checkPermission(FileSystemAction.WRITE, alluxioPath, true);

      // Check that the Alluxio Path does not exist
      boolean pathExists = false;
      try {
        mInodeTree.getInodeByPath(alluxioPath);
        pathExists = true;
      } catch (InvalidPathException e) {
        // Expected, continue
      }
      if (pathExists) {
        throw new InvalidPathException(
            ExceptionMessage.MOUNT_POINT_ALREADY_EXISTS.getMessage(alluxioPath));
      }

      mountInternal(alluxioPath, ufsPath);
      boolean loadMetadataSuceeded = false;
      try {
        // This will create the directory at alluxioPath
        loadMetadataDirectory(alluxioPath, false);
        loadMetadataSuceeded = true;
      } finally {
        if (!loadMetadataSuceeded) {
          unmountInternal(alluxioPath);
        }
        // Exception will be propagated from loadMetadataDirectory
      }
      AddMountPointEntry addMountPoint =
          AddMountPointEntry.newBuilder().setAlluxioPath(alluxioPath.toString())
              .setUfsPath(ufsPath.toString()).build();
      writeJournalEntry(JournalEntry.newBuilder().setAddMountPoint(addMountPoint).build());
      flushJournal();
      MasterContext.getMasterSource().incPathsMounted(1);
    }
  }