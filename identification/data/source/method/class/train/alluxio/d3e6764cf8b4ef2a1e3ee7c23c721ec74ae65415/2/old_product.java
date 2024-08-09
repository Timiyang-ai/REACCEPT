public long createFile(AlluxioURI path, CreateFileOptions options)
      throws AccessControlException, InvalidPathException, FileAlreadyExistsException,
          BlockInfoException, IOException, FileDoesNotExistException {
    MasterContext.getMasterSource().incCreateFileOps(1);
    synchronized (mInodeTree) {
      mPermissionChecker.checkParentPermission(FileSystemAction.WRITE, path);
      if (!options.isMetadataLoad()) {
        mMountTable.checkUnderWritableMountPoint(path);
      }
      InodeTree.CreatePathResult createResult = createFileInternal(path, options);
      List<Inode<?>> created = createResult.getCreated();

      writeJournalEntry(mDirectoryIdGenerator.toJournalEntry());
      journalCreatePathResult(createResult);
      flushJournal();
      return created.get(created.size() - 1).getId();
    }
  }