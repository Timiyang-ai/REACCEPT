public void mount(AlluxioURI tachyonPath, AlluxioURI ufsPath)
      throws FileAlreadyExistsException, InvalidPathException, IOException, AccessControlException {
    MasterContext.getMasterSource().incMountOps(1);
    synchronized (mInodeTree) {
      checkPermission(FileSystemAction.WRITE, tachyonPath, true);
      mountInternal(tachyonPath, ufsPath);
      boolean loadMetadataSuceeded = false;
      try {
        // This will create the directory at tachyonPath
        loadMetadataDirectory(tachyonPath, false);
        loadMetadataSuceeded = true;
      } finally {
        if (!loadMetadataSuceeded) {
          unmountInternal(tachyonPath);
        }
        // Exception will be propagated from loadMetadataDirectory
      }
      AddMountPointEntry addMountPoint =
          AddMountPointEntry.newBuilder().setTachyonPath(tachyonPath.toString())
              .setUfsPath(ufsPath.toString()).build();
      writeJournalEntry(JournalEntry.newBuilder().setAddMountPoint(addMountPoint).build());
      flushJournal();
      MasterContext.getMasterSource().incPathsMounted(1);
    }
  }