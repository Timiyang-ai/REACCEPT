public void setAttribute(TachyonURI path, SetAttributeOptions options)
      throws FileDoesNotExistException, AccessControlException, InvalidPathException {
    MasterContext.getMasterSource().incSetAttributeOps(1);
    synchronized (mInodeTree) {
      checkPermission(FileSystemAction.WRITE, path, false);
      long fileId = mInodeTree.getInodeByPath(path).getId();
      long opTimeMs = System.currentTimeMillis();
      setAttributeInternal(fileId, opTimeMs, options);
      SetAttributeEntry.Builder builder =
          SetAttributeEntry.newBuilder().setId(fileId).setOpTimeMs(opTimeMs);
      if (options.getPinned() != null) {
        builder.setPinned(options.getPinned());
      }
      if (options.getTtl() != null) {
        builder.setTtl(options.getTtl());
      }
      if (options.getPersisted() != null) {
        builder.setPersisted(options.getPersisted());
      }
      writeJournalEntry(JournalEntry.newBuilder().setSetAttribute(builder).build());
      flushJournal();
    }
  }