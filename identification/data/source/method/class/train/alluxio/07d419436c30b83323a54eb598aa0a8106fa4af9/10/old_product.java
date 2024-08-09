public void unmount(String tachyonPath) throws NotFoundException {
    // TODO(jiri): Persist files nested under tachyonPath and then void its namespace.
    mMountTable.delete(tachyonPath);
    writeJournalEntry(new DeleteMountPointEntry(tachyonPath));
    flushJournal();
  }