public long getFileId(AlluxioURI path) throws AccessControlException {
    Mutable<Long> flushCounter = new MutableObject<>(AsyncJournalWriter.INVALID_FLUSH_COUNTER);
    try (LockedInodePath inodePath = mInodeTree.lockInodePath(path, InodeTree.LockMode.WRITE)) {
      // This is WRITE locked, since loading metadata is possible.
      mPermissionChecker.checkPermission(Mode.Bits.READ, inodePath);
      loadMetadataIfNotExistAndJournal(inodePath,
          LoadMetadataOptions.defaults().setCreateAncestors(true), flushCounter);
      mInodeTree.ensureFullInodePath(inodePath, InodeTree.LockMode.READ);
      return inodePath.getInode().getId();
    } catch (InvalidPathException | FileDoesNotExistException e) {
      return IdUtils.INVALID_FILE_ID;
    } finally {
      // finally runs after resources are closed (unlocked).
      waitForJournalFlush(flushCounter.getValue());
    }
  }