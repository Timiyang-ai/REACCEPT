public boolean unmount(AlluxioURI alluxioPath)
      throws FileDoesNotExistException, InvalidPathException, IOException, AccessControlException {
    MasterContext.getMasterSource().incUnmountOps(1);
    synchronized (mInodeTree) {
      checkPermission(FileSystemAction.WRITE, alluxioPath, true);
      if (unmountInternal(alluxioPath)) {
        Inode<?> inode = mInodeTree.getInodeByPath(alluxioPath);
        // Use the internal delete API, setting {@code replayed} to false to prevent the delete
        // operations from being persisted in the UFS.
        long fileId = inode.getId();
        long opTimeMs = System.currentTimeMillis();
        deleteFileRecursiveInternal(fileId, true /* replayed */, opTimeMs);
        DeleteFileEntry deleteFile = DeleteFileEntry.newBuilder()
            .setId(fileId)
            .setRecursive(true)
            .setOpTimeMs(opTimeMs)
            .build();
        writeJournalEntry(JournalEntry.newBuilder().setDeleteFile(deleteFile).build());
        DeleteMountPointEntry deleteMountPoint = DeleteMountPointEntry.newBuilder()
            .setAlluxioPath(alluxioPath.toString())
            .build();
        writeJournalEntry(JournalEntry.newBuilder().setDeleteMountPoint(deleteMountPoint).build());
        flushJournal();
        MasterContext.getMasterSource().incPathsUnmounted(1);
        return true;
      }
    }
    return false;
  }