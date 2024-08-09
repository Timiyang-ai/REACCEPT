public boolean deleteFile(TachyonURI path, boolean recursive) throws IOException,
      FileDoesNotExistException, DirectoryNotEmptyException, InvalidPathException {
    MasterContext.getMasterSource().incDeletePathOps(1);
    synchronized (mInodeTree) {
      Inode inode = mInodeTree.getInodeByPath(path);
      long fileId = inode.getId();
      long opTimeMs = System.currentTimeMillis();
      boolean ret = deleteFileInternal(fileId, recursive, false, opTimeMs);
      DeleteFileEntry deleteFile = DeleteFileEntry.newBuilder()
          .setId(fileId)
          .setRecursive(recursive)
          .setOpTimeMs(opTimeMs)
          .build();
      writeJournalEntry(JournalEntry.newBuilder().setDeleteFile(deleteFile).build());
      flushJournal();
      return ret;
    }
  }