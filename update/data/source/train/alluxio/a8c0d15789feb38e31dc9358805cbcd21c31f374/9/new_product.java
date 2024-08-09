public InodeTree.CreatePathResult createDirectory(AlluxioURI path, CreateDirectoryOptions options)
      throws InvalidPathException, FileAlreadyExistsException, IOException, AccessControlException,
      FileDoesNotExistException {
    LOG.debug("createDirectory {} ", path);
    MasterContext.getMasterSource().incCreateDirectoriesOps(1);
    synchronized (mInodeTree) {
      try {
        mPermissionChecker.checkParentPermission(FileSystemAction.WRITE, path);
        if (!options.isMetadataLoad()) {
          mMountTable.checkUnderWritableMountPoint(path);
        }
        InodeTree.CreatePathResult createResult = mInodeTree.createPath(path, options);
        LOG.debug("writing journal entry for createDirectory {}", path);
        writeJournalEntry(mDirectoryIdGenerator.toJournalEntry());
        journalCreatePathResult(createResult);
        flushJournal();
        LOG.debug("flushed journal for createDirectory {}", path);
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