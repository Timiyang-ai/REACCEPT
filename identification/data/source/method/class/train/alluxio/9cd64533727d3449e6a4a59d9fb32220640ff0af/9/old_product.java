public void setAttribute(AlluxioURI path, SetAttributeOptions options)
      throws FileDoesNotExistException, AccessControlException, InvalidPathException {
    MasterContext.getMasterSource().incSetAttributeOps(1);
    synchronized (mInodeTree) {
      long fileId = mInodeTree.getInodeByPath(path).getId();
      long opTimeMs = System.currentTimeMillis();

      Inode targetInode = mInodeTree.getInodeByPath(path);
      if (options.isRecursive() && targetInode.isDirectory()) {
        List<Inode> inodeChildren =
            mInodeTree.getInodeChildrenRecursive((InodeDirectory) targetInode);
        for (Inode inode : inodeChildren) {
          if (options.getGroup() != null
              || options.getPermission() != Constants.INVALID_PERMISSION) {
            checkOwner(path);
          }
          checkPermission(FileSystemAction.WRITE, mInodeTree.getPath(inode), false);
        }
        for (Inode inode : inodeChildren) {
          long id = inode.getId();
          setAttributeInternal(id, opTimeMs, options);
          journalSetAttribute(id, opTimeMs, options);
        }
      }
      if (options.getOwner() != null) {
        PermissionChecker.checkSuperuser(getClientUser(), getGroups(getClientUser()));
      }
      if (options.getGroup() != null || options.getPermission() != Constants.INVALID_PERMISSION) {
        checkOwner(path);
      }
      checkPermission(FileSystemAction.WRITE, path, false);
      setAttributeInternal(fileId, opTimeMs, options);
      journalSetAttribute(fileId, opTimeMs, options);
    }
  }