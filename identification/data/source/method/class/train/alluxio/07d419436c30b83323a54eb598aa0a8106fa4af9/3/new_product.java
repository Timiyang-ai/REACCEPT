public boolean unmount(TachyonURI tachyonPath) throws FileDoesNotExistException,
      InvalidPathException {
    synchronized (mInodeTree) {
      if (mMountTable.delete(tachyonPath)) {
        // TODO(jiri): Delete metadata for the Tachyon namespace nested under tachyonPath.
        writeJournalEntry(new DeleteMountPointEntry(tachyonPath));
        flushJournal();
        return true;
      }
    }
    return false;
  }