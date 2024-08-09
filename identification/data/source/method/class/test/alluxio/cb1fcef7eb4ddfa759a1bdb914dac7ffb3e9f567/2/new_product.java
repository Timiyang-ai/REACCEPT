public boolean deleteFile(long fileId, boolean recursive)
      throws IOException, FileDoesNotExistException, DirectoryNotEmptyException,
      AccessControlException {
    MasterContext.getMasterSource().incDeletePathOps(1);
    synchronized (mInodeTree) {
      TachyonURI path = mInodeTree.getPath(mInodeTree.getInodeById(fileId));
      try {
        checkPermission(FileSystemAction.WRITE, path, true);
      } catch (InvalidPathException e) {
        LOG.warn("Invalid path {} for checking permission: " + e.getMessage(), path);
      }
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