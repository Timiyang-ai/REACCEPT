public List<FileInfo> listStatus(AlluxioURI path, ListStatusOptions listStatusOptions)
      throws AccessControlException, FileDoesNotExistException, InvalidPathException {
    mMasterSource.incGetFileInfoOps(1);
    long flushCounter = AsyncJournalWriter.INVALID_FLUSH_COUNTER;
    try (LockedInodePath inodePath = mInodeTree.lockInodePath(path, InodeTree.LockMode.WRITE)) {
      // This is WRITE locked, since loading metadata is possible.
      mPermissionChecker.checkPermission(Mode.Bits.READ, inodePath);

      LoadMetadataOptions loadMetadataOptions =
          LoadMetadataOptions.defaults().setCreateAncestors(true).setLoadDirectChildren(
              listStatusOptions.getLoadMetadataType() != LoadMetadataType.Never);
      Inode<?> inode;
      if (inodePath.fullPathExists()) {
        inode = inodePath.getInode();
        if (inode.isDirectory()
            && listStatusOptions.getLoadMetadataType() != LoadMetadataType.Always
            && ((InodeDirectory) inode).isDirectChildrenLoaded()) {
          loadMetadataOptions.setLoadDirectChildren(false);
        }
      }

      flushCounter = loadMetadataIfNotExistAndJournal(inodePath, loadMetadataOptions);
      mInodeTree.ensureFullInodePath(inodePath, InodeTree.LockMode.READ);
      inode = inodePath.getInode();

      List<FileInfo> ret = new ArrayList<>();
      if (inode.isDirectory()) {
        TempInodePathForDescendant tempInodePath = new TempInodePathForDescendant(inodePath);
        mPermissionChecker.checkPermission(Mode.Bits.EXECUTE, inodePath);
        for (Inode<?> child : ((InodeDirectory) inode).getChildren()) {
          child.lockRead();
          try {
            // the path to child for getPath should already be locked.
            tempInodePath.setDescendant(child, mInodeTree.getPath(child));
            ret.add(getFileInfoInternal(tempInodePath));
          } finally {
            child.unlockRead();
          }
        }
      } else {
        ret.add(getFileInfoInternal(inodePath));
      }
      mMasterSource.incFileInfosGot(ret.size());
      return ret;
    } finally {
      // finally runs after resources are closed (unlocked).
      waitForJournalFlush(flushCounter);
    }
  }