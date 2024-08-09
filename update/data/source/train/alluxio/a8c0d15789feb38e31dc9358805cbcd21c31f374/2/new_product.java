public boolean delete(AlluxioURI path, boolean recursive)
      throws IOException, FileDoesNotExistException, DirectoryNotEmptyException,
          InvalidPathException, AccessControlException {
    MasterContext.getMasterSource().incDeletePathOps(1);
    synchronized (mInodeTree) {
      mPermissionChecker.checkParentPermission(FileSystemAction.WRITE, path);
      mMountTable.checkUnderWritableMountPoint(path);
      Inode<?> inode = mInodeTree.getInodeByPath(path);
      long fileId = inode.getId();
      long opTimeMs = System.currentTimeMillis();
      boolean ret = deleteInternal(fileId, recursive, false, opTimeMs);
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