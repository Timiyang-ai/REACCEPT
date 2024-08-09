public boolean deleteFile(long fileId, boolean recursive)
      throws TachyonException, FileDoesNotExistException {
    MasterContext.getMasterSource().incDeleteFileOps();
    synchronized (mInodeTree) {
      long opTimeMs = System.currentTimeMillis();
      boolean ret = deleteFileInternal(fileId, recursive, true, opTimeMs);
      writeJournalEntry(new DeleteFileEntry(fileId, recursive, true, opTimeMs));
      flushJournal();
      return ret;
    }
  }