public long getFileId(AlluxioURI path) throws AccessControlException {
    try (JournalContext journalContext = createJournalContext();
        LockedInodePath inodePath = mInodeTree.lockInodePath(path, InodeTree.LockMode.WRITE)) {
      // This is WRITE locked, since loading metadata is possible.
      mPermissionChecker.checkPermission(Mode.Bits.READ, inodePath);
      loadMetadataIfNotExistAndJournal(inodePath,
          LoadMetadataOptions.defaults().setCreateAncestors(true), journalContext);
      mInodeTree.ensureFullInodePath(inodePath, InodeTree.LockMode.READ);
      return inodePath.getInode().getId();
    } catch (InvalidPathException | FileDoesNotExistException e) {
      return IdUtils.INVALID_FILE_ID;
    }
  }