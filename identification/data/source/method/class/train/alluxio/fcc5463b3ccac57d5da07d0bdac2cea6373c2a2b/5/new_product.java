public List<FileInfo> listStatus(AlluxioURI path, ListStatusOptions listStatusOptions)
      throws AccessControlException, FileDoesNotExistException, InvalidPathException {
    Metrics.GET_FILE_INFO_OPS.inc();
    try (JournalContext journalContext = createJournalContext();
        LockedInodePath inodePath = mInodeTree.lockInodePath(path, InodeTree.LockMode.READ)) {
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

      loadMetadataIfNotExistAndJournal(inodePath, loadMetadataOptions, journalContext);
      mInodeTree.ensureFullInodePath(inodePath, InodeTree.LockMode.READ);
      inode = inodePath.getInode();

      List<FileInfo> ret = new ArrayList<>();
      if (inode.isDirectory()) {
        TempInodePathForDescendant tempInodePath = new TempInodePathForDescendant(inodePath);
        mPermissionChecker.checkPermission(Mode.Bits.EXECUTE, inodePath);
        for (Inode<?> child : ((InodeDirectory) inode).getChildren()) {
          child.lockReadAndCheckParent(inode);
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
      Metrics.FILE_INFOS_GOT.inc();
      return ret;
    }
  }