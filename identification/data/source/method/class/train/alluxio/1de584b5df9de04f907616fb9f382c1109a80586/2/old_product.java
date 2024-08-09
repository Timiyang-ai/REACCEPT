public CreatePathResult createPath(RpcContext rpcContext, LockedInodePath inodePath,
      CreatePathOptions<?> options) throws FileAlreadyExistsException, BlockInfoException,
      InvalidPathException, IOException, FileDoesNotExistException {
    // TODO(gpang): consider splitting this into createFilePath and createDirectoryPath, with a
    // helper method for the shared logic.
    AlluxioURI path = inodePath.getUri();
    if (path.isRoot()) {
      String errorMessage = ExceptionMessage.FILE_ALREADY_EXISTS.getMessage(path);
      LOG.error(errorMessage);
      throw new FileAlreadyExistsException(errorMessage);
    }
    if (inodePath.fullPathExists()) {
      if (!(options instanceof CreateDirectoryOptions)
          || !((CreateDirectoryOptions) options).isAllowExists()) {
        throw new FileAlreadyExistsException(path);
      }
    }

    if (options instanceof CreateFileOptions) {
      CreateFileOptions fileOptions = (CreateFileOptions) options;
      if (fileOptions.getBlockSizeBytes() < 1) {
        throw new BlockInfoException("Invalid block size " + fileOptions.getBlockSizeBytes());
      }
    }

    if (!(inodePath instanceof MutableLockedInodePath)) {
      throw new InvalidPathException(
          ExceptionMessage.NOT_MUTABLE_INODE_PATH.getMessage(inodePath.getUri()));
    }

    LOG.debug("createPath {}", path);

    TraversalResult traversalResult = traverseToInode(inodePath, inodePath.getLockMode());
    MutableLockedInodePath extensibleInodePath = (MutableLockedInodePath) inodePath;
    String[] pathComponents = extensibleInodePath.getPathComponents();
    String name = path.getName();

    // pathIndex is the index into pathComponents where we start filling in the path from the inode.
    int pathIndex = extensibleInodePath.size();
    if (pathIndex < pathComponents.length - 1) {
      // The immediate parent was not found. If it's not recursive, we throw an exception here.
      // Otherwise we add the remaining path components to the list of components to create.
      if (!options.isRecursive()) {
        final String msg = new StringBuilder().append("File ").append(path)
            .append(" creation failed. Component ")
            .append(pathIndex).append("(")
            .append(pathComponents[pathIndex])
            .append(") does not exist").toString();
        LOG.error("FileDoesNotExistException: {}", msg);
        throw new FileDoesNotExistException(msg);
      }
    }
    // The ancestor inode (parent or ancestor) of the target path.
    Inode<?> ancestorInode = extensibleInodePath.getAncestorInode();
    if (!ancestorInode.isDirectory()) {
      throw new InvalidPathException("Could not traverse to parent directory of path " + path
          + ". Component " + pathComponents[pathIndex - 1] + " is not a directory.");
    }
    InodeDirectory currentInodeDirectory = (InodeDirectory) ancestorInode;

    List<Inode<?>> createdInodes = new ArrayList<>();
    List<Inode<?>> modifiedInodes = new ArrayList<>();
    if (options.isPersisted()) {
      // Synchronously persist directories. These inodes are already READ locked.
      for (Inode inode : traversalResult.getNonPersisted()) {
        // This cast is safe because we've already verified that the file inode doesn't exist.
        syncPersistDirectory(rpcContext, (InodeDirectory) inode);
      }
    }
    if ((pathIndex < (pathComponents.length - 1) || currentInodeDirectory.getChild(name) == null)
        && options.getOperationTimeMs() > currentInodeDirectory.getLastModificationTimeMs()) {
      // (1) There are components in parent paths that need to be created. Or
      // (2) The last component of the path needs to be created.
      // In these two cases, the last traversed Inode will be modified if the new timestamp is after
      // the existing last modified time.
      currentInodeDirectory.setLastModificationTimeMs(options.getOperationTimeMs());
      modifiedInodes.add(currentInodeDirectory);

      File.InodeLastModificationTimeEntry inodeLastModificationTime =
          File.InodeLastModificationTimeEntry.newBuilder().setId(currentInodeDirectory.getId())
              .setLastModificationTimeMs(options.getOperationTimeMs()).build();
      rpcContext.journal(Journal.JournalEntry.newBuilder()
          .setInodeLastModificationTime(inodeLastModificationTime).build());
    }

    // Fill in the ancestor directories that were missing.
    // NOTE, we set the mode of missing ancestor directories to be the default value, rather
    // than inheriting the option of the final file to create, because it may not have
    // "execute" permission.
    CreateDirectoryOptions missingDirOptions = CreateDirectoryOptions.defaults()
        .setMountPoint(false)
        .setPersisted(options.isPersisted())
        .setOperationTimeMs(options.getOperationTimeMs())
        .setOwner(options.getOwner())
        .setGroup(options.getGroup())
        .setTtl(options.getTtl())
        .setTtlAction(options.getTtlAction());
    for (int k = pathIndex; k < (pathComponents.length - 1); k++) {
      InodeDirectory dir = null;
      while (dir == null) {
        dir = InodeDirectory.create(
            mDirectoryIdGenerator.getNewDirectoryId(rpcContext.getJournalContext()),
            currentInodeDirectory.getId(), pathComponents[k], missingDirOptions);
        // Lock the newly created inode before subsequent operations, and add it to the lock group.
        extensibleInodePath.getLockList().lockWriteAndCheckNameAndParent(dir,
            currentInodeDirectory, pathComponents[k]);

        if (!currentInodeDirectory.addChild(dir)) {
          // The child directory inode already exists. Get the existing child inode.
          extensibleInodePath.getLockList().unlockLast();

          dir =
              (InodeDirectory) currentInodeDirectory.getChildReadLock(pathComponents[k],
                  extensibleInodePath.getLockList());
          if (dir == null) {
            // Could not get the child inode. Continue and try again.
            continue;
          }
        } else {
          try {
            // Successfully added the child, while holding the write lock.
            dir.setPinned(currentInodeDirectory.isPinned());

            // if the parent has default ACL, copy that default ACL as the new directory's default
            // and access acl.
            if (!options.isMetadataLoad()) {
              DefaultAccessControlList dAcl = currentInodeDirectory.getDefaultACL();
              if (!dAcl.isEmpty()) {
                Pair<AccessControlList, DefaultAccessControlList> pair = dAcl.generateChildDirACL();
                dir.setInternalAcl(pair.getFirst());
                dir.setDefaultACL(pair.getSecond());
              }
            }
            if (options.isPersisted()) {
              // Do not journal the persist entry, since a creation entry will be journaled instead.
              syncPersistDirectory(RpcContext.NOOP, dir);
            }
          } catch (Exception e) {
            // Failed to persist the directory, so remove it from the parent.
            currentInodeDirectory.removeChild(dir);
            throw e;
          }
          // Journal the new inode.
          rpcContext.getJournalContext().append(dir.toJournalEntry());
          mInodes.add(dir);

          // After creation and journaling, downgrade to a read lock.
          extensibleInodePath.getLockList().downgradeLast();
        }
      }

      createdInodes.add(dir);
      currentInodeDirectory = dir;
    }

    // Create the final path component. First we need to make sure that there isn't already a file
    // here with that name. If there is an existing file that is a directory and we're creating a
    // directory, update persistence property of the directories if needed, otherwise, throw
    // FileAlreadyExistsException unless options.allowExists is true.
    Inode<?> lastInode = null;
    while (lastInode == null) {
      // Try to lock the last inode with the lock mode of the path.
      switch (extensibleInodePath.getLockMode()) {
        case READ:
          lastInode = currentInodeDirectory.getChildReadLock(name,
              extensibleInodePath.getLockList());
          break;
        case WRITE_PARENT:
        case WRITE:
          lastInode = currentInodeDirectory.getChildWriteLock(name,
              extensibleInodePath.getLockList());
          break;
        default:
          // This should not be reachable.
          LOG.warn("Unexpected lock mode encountered: {}", extensibleInodePath.getLockMode());
      }
      if (lastInode != null) {
        // inode to create already exists
        // We need to remove the last inode from the locklist because it was locked during
        // traversal and locked here again
        extensibleInodePath.getLockList().unlockLast();
        if (lastInode.isDirectory() && options instanceof CreateDirectoryOptions && !lastInode
            .isPersisted() && options.isPersisted()) {
          // The final path component already exists and is not persisted, so it should be added
          // to the non-persisted Inodes of traversalResult.
          syncPersistDirectory(rpcContext, (InodeDirectory) lastInode);

        } else if (!lastInode.isDirectory() || !(options instanceof CreateDirectoryOptions
            && ((CreateDirectoryOptions) options).isAllowExists())) {
          String errorMessage = ExceptionMessage.FILE_ALREADY_EXISTS.getMessage(path);
          LOG.error(errorMessage);
          throw new FileAlreadyExistsException(errorMessage);
        }

      } else {
        // create the new inode, with a write lock
        if (options instanceof CreateDirectoryOptions) {
          CreateDirectoryOptions directoryOptions = (CreateDirectoryOptions) options;
          lastInode = InodeDirectory.create(
              mDirectoryIdGenerator.getNewDirectoryId(rpcContext.getJournalContext()),
              currentInodeDirectory.getId(), name, directoryOptions);

          // Lock the created inode before subsequent operations, and add it to the lock group.

          extensibleInodePath.getLockList().lockWriteAndCheckNameAndParent(lastInode,
              currentInodeDirectory, name);

          // if the parent has default ACL, copy that default ACL as the new directory's default
          // and access acl.
          DefaultAccessControlList dAcl = currentInodeDirectory.getDefaultACL();
          if (!dAcl.isEmpty()) {
            Pair<AccessControlList, DefaultAccessControlList> pair = dAcl.generateChildDirACL();
            InodeDirectory lastInodeDirectory = (InodeDirectory) lastInode;
            lastInodeDirectory.setInternalAcl(pair.getFirst());
            lastInodeDirectory.setDefaultACL(pair.getSecond());
          }

          if (directoryOptions.isPersisted()) {
            // Do not journal the persist entry, since a creation entry will be journaled instead.
            // TODO(david): remove this call to syncPersistDirectory to improve performance
            // of recursive ls.
            syncPersistDirectory(RpcContext.NOOP, (InodeDirectory) lastInode);
          }
        } else if (options instanceof CreateFileOptions) {
          CreateFileOptions fileOptions = (CreateFileOptions) options;
          lastInode = InodeFile.create(mContainerIdGenerator.getNewContainerId(),
              currentInodeDirectory.getId(), name, System.currentTimeMillis(), fileOptions);
          // Lock the created inode before subsequent operations, and add it to the lock group.

          extensibleInodePath.getLockList().lockWriteAndCheckNameAndParent(lastInode,
              currentInodeDirectory, name);

          // if the parent has a default ACL, copy that default ACL as the new file's access ACL.
          DefaultAccessControlList dAcl = currentInodeDirectory.getDefaultACL();
          if (!dAcl.isEmpty()) {
            AccessControlList acl = dAcl.generateChildFileACL();
            lastInode.setInternalAcl(acl);
          }

          if (fileOptions.isCacheable()) {
            ((InodeFile) lastInode).setCacheable(true);
          }
        }
        lastInode.setPinned(currentInodeDirectory.isPinned());

        // Update state while holding the write lock.
        // lastInode should be added to mInodes before getting added to its parent list, because it
        // becomes visible at this point.
        mInodes.add(lastInode);
        if (!currentInodeDirectory.addChild(lastInode)) {
          // Could not add the child inode to the parent. Continue and try again.
          // Cleanup is not necessary, since other state is updated later, after a successful add.
          mInodes.remove(lastInode);
          extensibleInodePath.getLockList().unlockLast();
          lastInode = null;
          continue;
        }

        if (lastInode instanceof InodeFile) {
          if (currentInodeDirectory.isPinned()) {
            // Update set of pinned file ids.
            mPinnedInodeFileIds.add(lastInode.getId());
          }
        }

        // Journal the new inode.
        rpcContext.getJournalContext().append(lastInode.toJournalEntry());

        createdInodes.add(lastInode);
      }
    }

    LOG.debug("createFile: File Created: {} parent: {}", lastInode, currentInodeDirectory);
    return new CreatePathResult(modifiedInodes, createdInodes);
  }