public InodeTree.CreatePathResult createDirectory(AlluxioURI path, CreateDirectoryOptions options)
      throws InvalidPathException, FileAlreadyExistsException, IOException, AccessControlException,
      FileDoesNotExistException {
    LOG.debug("createDirectory {} ", path);
    MasterContext.getMasterSource().incCreateDirectoriesOps(1);
    synchronized (mInodeTree) {
      mPermissionChecker.checkParentPermission(FileSystemAction.WRITE, path);
      if (!options.isMetadataLoad()) {
        mMountTable.checkUnderWritableMountPoint(path);
      }
      InodeTree.CreatePathResult createResult = createDirectoryInternal(path, options);
      writeJournalEntry(mDirectoryIdGenerator.toJournalEntry());
      journalCreatePathResult(createResult);
      flushJournal();
      MasterContext.getMasterSource().incDirectoriesCreated(1);
      return createResult;
    }
  }