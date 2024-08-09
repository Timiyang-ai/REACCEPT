public void setAttribute(AlluxioURI path, SetAttributeOptions options)
      throws FileDoesNotExistException, AccessControlException, InvalidPathException {
    MasterContext.getMasterSource().incSetAttributeOps(1);
    // for chown
    boolean superuserRequired = options.getOwner() != null;
    // for chgrp, chmod
    boolean ownerRequired =
        (options.getGroup() != null) || (options.getPermission() != Constants.INVALID_PERMISSION);
    synchronized (mInodeTree) {
      checkSetAttributePermission(path, superuserRequired, ownerRequired);

      long fileId = mInodeTree.getInodeByPath(path).getId();
      long opTimeMs = System.currentTimeMillis();
      Inode targetInode = mInodeTree.getInodeByPath(path);
      if (options.isRecursive() && targetInode.isDirectory()) {
        List<Inode> inodeChildren =
            mInodeTree.getInodeChildrenRecursive((InodeDirectory) targetInode);
        for (Inode inode : inodeChildren) {
          checkSetAttributePermission(mInodeTree.getPath(inode), superuserRequired, ownerRequired);
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