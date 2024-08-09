public long getFileId(AlluxioURI path) throws AccessControlException, FileDoesNotExistException {
    long flushCounter = AsyncJournalWriter.INVALID_FLUSH_COUNTER;
    try (InodePath inodePath = mInodeTree.lockInodePath(path, InodeTree.LockMode.READ)) {
      mPermissionChecker.checkPermission(FileSystemAction.READ, inodePath);
      flushCounter = loadMetadataIfNotExistAndJournal(inodePath);
      mInodeTree.ensureFullInodePath(inodePath, InodeTree.LockMode.READ);
      return inodePath.getInode().getId();
    } catch (InvalidPathException | FileDoesNotExistException e) {
      return IdUtils.INVALID_FILE_ID;
    } finally {
      // finally runs after resources are closed (unlocked).
      if (flushCounter != AsyncJournalWriter.INVALID_FLUSH_COUNTER) {
        waitForJournalFlush(flushCounter);
      }
    }
  }