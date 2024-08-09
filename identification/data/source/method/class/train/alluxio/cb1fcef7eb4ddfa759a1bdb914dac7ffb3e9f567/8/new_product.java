public boolean deleteFile(long fileId, boolean recursive) throws FileDoesNotExistException,
      TachyonException {
    MasterContext.getMasterSource().incDeleteFileOps();
    synchronized (mInodeTree) {
      long opTimeMs = System.currentTimeMillis();
      TachyonURI path = mInodeTree.getPath(mInodeTree.getInodeById(fileId));
      boolean ret = deleteFileInternal(fileId, recursive, true, opTimeMs);
      writeJournalEntry(new DeleteFileEntry(fileId, recursive, opTimeMs));
      flushJournal();
      LOG.debug("Deleted " + path);
      return ret;
    }
  }