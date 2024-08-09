public boolean rename(long fileId, TachyonURI dstPath)
      throws InvalidPathException, FileDoesNotExistException, IOException {
    MasterContext.getMasterSource().incRenameOps();
    synchronized (mInodeTree) {
      Inode srcInode = mInodeTree.getInodeById(fileId);
      TachyonURI srcPath = mInodeTree.getPath(srcInode);
      // Renaming path to itself is a no-op.
      if (srcPath.equals(dstPath)) {
        return true;
      }
      // Renaming the root is not allowed.
      if (srcPath.isRoot() || dstPath.isRoot()) {
        return false;
      }
      // Renaming across mount points is not allowed.
      String srcMount = mMountTable.getMountPoint(srcPath);
      String dstMount = mMountTable.getMountPoint(dstPath);
      if ((srcMount == null && dstMount != null)
          || (srcMount != null && dstMount == null)
          || (srcMount != null && dstMount != null && !srcMount.equals(dstMount))) {
        return false;
      }

      // Rename a path to one of its subpaths is not allowed. Check for that, by making sure
      // srcComponents isn't a prefix of dstComponents.
      String[] srcComponents = PathUtils.getPathComponents(srcPath.toString());
      String[] dstComponents = PathUtils.getPathComponents(dstPath.toString());
      if (srcComponents.length < dstComponents.length) {
        boolean isPrefix = true;
        for (int prefixInd = 0; prefixInd < srcComponents.length; prefixInd++) {
          if (!srcComponents[prefixInd].equals(dstComponents[prefixInd])) {
            isPrefix = false;
            break;
          }
        }
        if (isPrefix) {
          throw new InvalidPathException("Failed to rename: " + srcPath + " is a prefix of "
              + dstPath);
        }
      }

      TachyonURI dstParentURI = dstPath.getParent();

      // Get the inodes of the src and dst parents.
      Inode srcParentInode = mInodeTree.getInodeById(srcInode.getParentId());
      if (!srcParentInode.isDirectory()) {
        return false;
      }
      Inode dstParentInode = mInodeTree.getInodeByPath(dstParentURI);
      if (!dstParentInode.isDirectory()) {
        return false;
      }

      InodeDirectory dstParentDirectory = (InodeDirectory) dstParentInode;

      // Make sure destination path does not exist
      if (dstParentDirectory.getChild(dstComponents[dstComponents.length - 1]) != null) {
        return false;
      }

      // Now we remove srcInode from it's parent and insert it into dstPath's parent
      long opTimeMs = System.currentTimeMillis();
      renameInternal(fileId, dstPath, true, opTimeMs);

      writeJournalEntry(new RenameEntry(fileId, dstPath.getPath(), opTimeMs));
      flushJournal();

      LOG.info("Renamed " + srcPath + " to " + dstPath);
      return true;
    }
  }