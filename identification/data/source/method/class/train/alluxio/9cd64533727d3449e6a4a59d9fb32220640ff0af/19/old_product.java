public void setAttribute(TachyonURI path, SetAttributeOptions options)
      throws FileDoesNotExistException, AccessControlException, InvalidPathException {
    MasterContext.getMasterSource().incSetAttributeOps(1);
    synchronized (mInodeTree) {
      checkPermission(FileSystemAction.WRITE, path, false);
      long fileId = mInodeTree.getInodeByPath(path).getId();
      long opTimeMs = System.currentTimeMillis();

      Inode targetInode = mInodeTree.getInodeByPath(path);
      if (options.isRecursive() && targetInode.isDirectory()) {
        List<Inode> inodeChildren =
            mInodeTree.getInodeChildrenRecursive((InodeDirectory) targetInode);
        for (Inode inode : inodeChildren) {
          checkOwner(mInodeTree.getPath(inode));
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