public boolean deleteFile(long fileId, boolean recursive)
      throws TachyonException, FileDoesNotExistException {
    MasterContext.getMasterSource().incDeleteFileOps();
    synchronized (mInodeTree) {
      long opTimeMs = System.currentTimeMillis();
      boolean ret = deleteFileInternal(fileId, recursive, opTimeMs);
      writeJournalEntry(new DeleteFileEntry(fileId, recursive, opTimeMs));
      flushJournal();
      return ret;
    }
  }