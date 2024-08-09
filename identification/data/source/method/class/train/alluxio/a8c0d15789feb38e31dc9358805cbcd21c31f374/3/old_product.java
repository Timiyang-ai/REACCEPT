public InodeTree.CreatePathResult mkdir(AlluxioURI path, CreateDirectoryOptions options)
      throws InvalidPathException, FileAlreadyExistsException, IOException, AccessControlException {
    LOG.debug("mkdir {} ", path);
    MasterContext.getMasterSource().incCreateDirectoriesOps(1);
    synchronized (mInodeTree) {
      try {
        checkPermission(FileSystemAction.WRITE, path, true);
        CreatePathOptions createPathOptions = new CreatePathOptions.Builder(MasterContext.getConf())
            .setAllowExists(options.isAllowExists())
            .setDirectory(true)
            .setPersisted(options.isPersisted())
            .setRecursive(options.isRecursive())
            .setOperationTimeMs(options.getOperationTimeMs())
            .setPermissionStatus(PermissionStatus.get(MasterContext.getConf(), true))
            .setMountPoint(mMountTable.isMountPoint(path))
            .build();
        InodeTree.CreatePathResult createResult = mInodeTree.createPath(path, createPathOptions);

        LOG.debug("writing journal entry for mkdir {}", path);
        writeJournalEntry(mDirectoryIdGenerator.toJournalEntry());
        journalCreatePathResult(createResult);
        flushJournal();
        LOG.debug("flushed journal for mkdir {}", path);
        MasterContext.getMasterSource().incDirectoriesCreated(1);
        return createResult;
      } catch (BlockInfoException e) {
        // Since we are creating a directory, the block size is ignored, no such exception should
        // happen.
        Throwables.propagate(e);
      }
    }
    return null;
  }