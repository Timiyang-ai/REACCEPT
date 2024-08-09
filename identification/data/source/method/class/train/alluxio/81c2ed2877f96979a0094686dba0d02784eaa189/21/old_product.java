public FileInfo getFileInfo(AlluxioURI path)
      throws FileDoesNotExistException, InvalidPathException, AccessControlException {
    MasterContext.getMasterSource().incGetFileInfoOps(1);
    synchronized (mInodeTree) {
      mPermissionChecker.checkPermission(FileSystemAction.READ, path);
      // getFileInfo should load from ufs if the file does not exist
      getFileId(path);

      if (!mInodeTree.inodePathExists(path)) {
        try {
          loadMetadata(path, LoadMetadataOptions.defaults().setCreateAncestors(true));
        } catch (Exception e) {
          // TODO(peis): consider throwing a metadata loading failure exception here.
          LOG.error("Failed to load metadata at {}", path, e);
        }
      }

      Inode<?> inode = mInodeTree.getInodeByPath(path);
      return getFileInfoInternal(inode);
    }
  }