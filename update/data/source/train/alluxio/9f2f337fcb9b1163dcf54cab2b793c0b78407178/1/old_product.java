public void setState(TachyonURI path, SetAttributeOptions options)
      throws FileDoesNotExistException, AccessControlException, InvalidPathException {
    MasterContext.getMasterSource().incSetStateOps(1);
    synchronized (mInodeTree) {
      checkPermission(FileSystemAction.WRITE, path, false);
      long fileId = mInodeTree.getInodeByPath(path).getId();
      long opTimeMs = System.currentTimeMillis();
      setStateInternal(fileId, opTimeMs, options);
      SetStateEntry.Builder setState =
          SetStateEntry.newBuilder().setId(fileId).setOpTimeMs(opTimeMs);
      if (options.hasPinned()) {
        setState.setPinned(options.getPinned());
      }
      if (options.hasTtl()) {
        setState.setTtl(options.getTtl());
      }
      if (options.hasPersisted()) {
        setState.setPersisted(options.getPersisted());
      }
      writeJournalEntry(JournalEntry.newBuilder().setSetState(setState).build());
      flushJournal();
    }
  }