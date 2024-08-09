public void setAttribute(AlluxioURI path, SetAttributeOptions options)
      throws FileDoesNotExistException, AccessControlException, InvalidPathException {
    MasterContext.getMasterSource().incSetAttributeOps(1);
    synchronized (mInodeTree) {
      checkPermission(FileSystemAction.WRITE, path, false);
      if (options.getOwner() != null || options.getGroup() != null
          || options.getPermission() != Constants.INVALID_PERMISSION) {
        checkOwner(path);
      }

      long fileId = mInodeTree.getInodeByPath(path).getId();
      long opTimeMs = System.currentTimeMillis();
      Inode targetInode = mInodeTree.getInodeByPath(path);
      if (options.isRecursive() && targetInode.isDirectory()) {
        List<Inode> inodeChildren =
            mInodeTree.getInodeChildrenRecursive((InodeDirectory) targetInode);
        for (Inode inode : inodeChildren) {
          checkPermission(FileSystemAction.WRITE, mInodeTree.getPath(inode), false);
          if (options.getOwner() != null || options.getGroup() != null
              || options.getPermission() != Constants.INVALID_PERMISSION) {
            checkOwner(path);
          }
        }
        for (Inode inode : inodeChildren) {
          long id = inode.getId();
          setAttributeInternal(id, opTimeMs, options);
          journalSetAttribute(id, opTimeMs, options);
        }
      }
      setAttributeInternal(fileId, opTimeMs, options);
      journalSetAttribute(fileId, opTimeMs, options);
    }
  }