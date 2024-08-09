public void mount(AlluxioURI alluxioPath, AlluxioURI ufsPath, MountOptions options)
      throws FileAlreadyExistsException, InvalidPathException, IOException, AccessControlException {
    MasterContext.getMasterSource().incMountOps(1);
    synchronized (mInodeTree) {
      checkPermission(FileSystemAction.WRITE, alluxioPath, true);
      mountInternal(alluxioPath, ufsPath, options);
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
              .setUfsPath(ufsPath.toString()).setOptions(options.toProto()).build();
      writeJournalEntry(JournalEntry.newBuilder().setAddMountPoint(addMountPoint).build());
      flushJournal();
      MasterContext.getMasterSource().incPathsMounted(1);
    }
  }