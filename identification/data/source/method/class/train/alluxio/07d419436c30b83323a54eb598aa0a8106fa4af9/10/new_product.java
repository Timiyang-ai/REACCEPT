public void unmount(TachyonURI tachyonPath) throws FileDoesNotExistException,
      InvalidPathException, NotFoundException {
    mMountTable.delete(tachyonPath);
    long fileId = getFileId(tachyonPath);
    // TODO(jiri): Delete the files here.
    writeJournalEntry(new DeleteMountPointEntry(tachyonPath));
    flushJournal();
  }