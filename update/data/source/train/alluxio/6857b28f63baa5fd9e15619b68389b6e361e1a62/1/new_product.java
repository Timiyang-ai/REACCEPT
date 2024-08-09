public InodeTree.CreatePathResult mkdir(AlluxioURI path, CreateDirectoryOptions options)
      throws InvalidPathException, FileAlreadyExistsException, IOException, AccessControlException {
    LOG.debug("mkdir {} ", path);
    MasterContext.getMasterSource().incCreateDirectoriesOps(1);
    synchronized (mInodeTree) {
      try {
        checkPermission(FileSystemAction.WRITE, path, true);
        InodeTree.CreatePathResult createResult = mInodeTree.createPath(path, options);

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