public void rename(AlluxioURI srcPath, AlluxioURI dstPath) throws FileAlreadyExistsException,
      FileDoesNotExistException, InvalidPathException, IOException, AccessControlException {
    MasterContext.getMasterSource().incRenamePathOps(1);
    synchronized (mInodeTree) {
      mPermissionChecker.checkParentPermission(FileSystemAction.WRITE, srcPath);
      mPermissionChecker.checkParentPermission(FileSystemAction.WRITE, dstPath);
      mMountTable.checkUnderWritableMountPoint(srcPath);
      mMountTable.checkUnderWritableMountPoint(dstPath);
      Inode<?> srcInode = mInodeTree.getInodeByPath(srcPath);
      // Renaming path to itself is a no-op.
      if (srcPath.equals(dstPath)) {
        return;
      }
      // Renaming the root is not allowed.
      if (srcPath.isRoot()) {
        throw new InvalidPathException(ExceptionMessage.ROOT_CANNOT_BE_RENAMED.getMessage());
      }
      if (dstPath.isRoot()) {
        throw new InvalidPathException(ExceptionMessage.RENAME_CANNOT_BE_TO_ROOT.getMessage());
      }
      // Renaming across mount points is not allowed.
      String srcMount = mMountTable.getMountPoint(srcPath);
      String dstMount = mMountTable.getMountPoint(dstPath);
      if ((srcMount == null && dstMount != null) || (srcMount != null && dstMount == null)
          || (srcMount != null && dstMount != null && !srcMount.equals(dstMount))) {
        throw new InvalidPathException(ExceptionMessage.RENAME_CANNOT_BE_ACROSS_MOUNTS.getMessage(
            srcPath, dstPath));
      }
      // Renaming onto a mount point is not allowed.
      if (mMountTable.isMountPoint(dstPath)) {
        throw new InvalidPathException(
            ExceptionMessage.RENAME_CANNOT_BE_ONTO_MOUNT_POINT.getMessage(dstPath));
      }
      // Renaming a path to one of its subpaths is not allowed. Check for that, by making sure
      // srcComponents isn't a prefix of dstComponents.
      if (PathUtils.hasPrefix(dstPath.getPath(), srcPath.getPath())) {
        throw new InvalidPathException(ExceptionMessage.RENAME_CANNOT_BE_TO_SUBDIRECTORY.getMessage(
            srcPath, dstPath));
      }

      AlluxioURI dstParentURI = dstPath.getParent();

      // Get the inodes of the src and dst parents.
      Inode<?> srcParentInode = mInodeTree.getInodeById(srcInode.getParentId());
      if (!srcParentInode.isDirectory()) {
        throw new InvalidPathException(
            ExceptionMessage.PATH_MUST_HAVE_VALID_PARENT.getMessage(srcPath));
      }
      Inode<?> dstParentInode = mInodeTree.getInodeByPath(dstParentURI);
      if (!dstParentInode.isDirectory()) {
        throw new InvalidPathException(
            ExceptionMessage.PATH_MUST_HAVE_VALID_PARENT.getMessage(dstPath));
      }

      // Make sure destination path does not exist
      InodeDirectory dstParentDirectory = (InodeDirectory) dstParentInode;
      String[] dstComponents = PathUtils.getPathComponents(dstPath.getPath());
      if (dstParentDirectory.getChild(dstComponents[dstComponents.length - 1]) != null) {
        throw new FileAlreadyExistsException(
            ExceptionMessage.FILE_ALREADY_EXISTS.getMessage(dstPath));
      }

      // Now we remove srcInode from it's parent and insert it into dstPath's parent
      long opTimeMs = System.currentTimeMillis();
      renameInternal(srcInode.getId(), dstPath, false, opTimeMs);

      RenameEntry rename = RenameEntry.newBuilder()
          .setId(srcInode.getId())
          .setDstPath(dstPath.getPath())
          .setOpTimeMs(opTimeMs)
          .build();
      writeJournalEntry(JournalEntry.newBuilder().setRename(rename).build());
      flushJournal();

      LOG.debug("Renamed {} to {}", srcPath, dstPath);
    }
  }