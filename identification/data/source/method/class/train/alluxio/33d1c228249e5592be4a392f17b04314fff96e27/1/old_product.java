public void mount(AlluxioURI alluxioPath, AlluxioURI ufsPath, MountOptions options)
      throws FileAlreadyExistsException, InvalidPathException, IOException, AccessControlException {
    MasterContext.getMasterSource().incMountOps(1);
    synchronized (mInodeTree) {
      // Permission checking is performed in loadDirectoryMetadata
      mMountTable.checkUnderWritableMountPoint(alluxioPath);
      // Check that the Alluxio Path does not exist
      // TODO(calvin): Provide a cleaner way to check for existence (ALLUXIO-1830)
      boolean pathExists = false;
      try {
        mInodeTree.getInodeByPath(alluxioPath);
        pathExists = true;
      } catch (FileDoesNotExistException e) {
        // Expected, continue
      }
      if (pathExists) {
        // TODO(calvin): Add a test to validate this (ALLUXIO-1831)
        throw new InvalidPathException(
            ExceptionMessage.MOUNT_POINT_ALREADY_EXISTS.getMessage(alluxioPath));
      }

      mountInternal(alluxioPath, ufsPath, options);
      boolean loadMetadataSuceeded = false;
      try {
        // This will create the directory at alluxioPath
        loadDirectoryMetadata(alluxioPath, false);
        loadMetadataSuceeded = true;
      } catch (FileDoesNotExistException e) {
        // This exception should be impossible since we just mounted this path
        throw Throwables.propagate(e);
      } finally {
        if (!loadMetadataSuceeded) {
          unmountInternal(alluxioPath);
        }
        // Exception will be propagated from loadDirectoryMetadata
      }
      AddMountPointEntry addMountPoint =
          AddMountPointEntry.newBuilder().setAlluxioPath(alluxioPath.toString())
              .setUfsPath(ufsPath.toString()).setReadOnly(options.isReadOnly())
              .addAllProperties(options.getPropertiesForProto()).build();
      writeJournalEntry(JournalEntry.newBuilder().setAddMountPoint(addMountPoint).build());
      flushJournal();
      MasterContext.getMasterSource().incPathsMounted(1);
    }
  }