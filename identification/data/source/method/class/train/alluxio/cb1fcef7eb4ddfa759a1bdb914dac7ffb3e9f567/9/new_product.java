public boolean deleteFile(long fileId, boolean recursive)
      throws IOException, FileDoesNotExistException, ImproperOptionsException {
    MasterContext.getMasterSource().incDeleteFileOps();
    synchronized (mInodeTree) {
      long opTimeMs = System.currentTimeMillis();
      boolean ret = deleteFileInternal(fileId, recursive, false, opTimeMs);
      writeJournalEntry(new DeleteFileEntry(fileId, recursive, opTimeMs));
      flushJournal();
      return ret;
    }
  }