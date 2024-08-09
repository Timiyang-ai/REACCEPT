public CreatePathResult createPath(LockedInodePath inodePath, CreatePathOptions<?> options,
      JournalEntryAppender journalAppender)
      throws FileAlreadyExistsException, BlockInfoException, InvalidPathException, IOException,
      FileDoesNotExistException {
    AlluxioURI path = inodePath.getUri();
    if (path.isRoot()) {
      String errorMessage = ExceptionMessage.FILE_ALREADY_EXISTS.getMessage(path);
      LOG.error(errorMessage);
      throw new FileAlreadyExistsException(errorMessage);
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

    TraversalResult traversalResult = traverseToInode(inodePath, LockMode.WRITE);
    InodeLockList lockList = traversalResult.getInodeLockList();

    MutableLockedInodePath extensibleInodePath = (MutableLockedInodePath) inodePath;
    String[] pathComponents = extensibleInodePath.getPathComponents();
    String name = path.getName();

    // pathIndex is the index into pathComponents where we start filling in the path from the inode.
    int pathIndex = extensibleInodePath.getInodes().size();
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
    // These are inodes that already exist, that should be journaled as persisted.
    List<Inode<?>> existingNonPersisted = new ArrayList<>();
    // These are inodes to mark as persisted at the end of this method.
    List<Inode<?>> toPersistDirectories = new ArrayList<>();
    if (options.isPersisted()) {
      existingNonPersisted.addAll(traversalResult.getNonPersisted());

      // Synchronously persist directories. These inodes are already READ locked.
      for (Inode inode : traversalResult.getNonPersisted()) {
        syncPersistDirectory((InodeDirectory) inode, journalAppender);
      }
    }
    if (pathIndex < (pathComponents.length - 1) || currentInodeDirectory.getChild(name) == null) {
      // (1) There are components in parent paths that need to be created. Or
      // (2) The last component of the path needs to be created.
      // In these two cases, the last traversed Inode will be modified.
      modifiedInodes.add(currentInodeDirectory);

      File.InodeLastModificationTimeEntry inodeLastModificationTime =
          File.InodeLastModificationTimeEntry.newBuilder().setId(currentInodeDirectory.getId())
              .setLastModificationTimeMs(currentInodeDirectory.getLastModificationTimeMs()).build();
      journalAppender.append(
          Journal.JournalEntry.newBuilder().setInodeLastModificationTime(inodeLastModificationTime)
              .build());
    }

    // Fill in the ancestor directories that were missing.
    // NOTE, we set the mode of missing ancestor directories to be the default value, rather
    // than inheriting the option of the final file to create, because it may not have
    // "execute" permission.
    CreateDirectoryOptions missingDirOptions = CreateDirectoryOptions.defaults()
        .setMountPoint(false)
        .setPersisted(options.isPersisted())
        .setOwner(options.getOwner())
        .setGroup(options.getGroup());
    for (int k = pathIndex; k < (pathComponents.length - 1); k++) {
      InodeDirectory dir = null;
      while (dir == null) {
        dir = InodeDirectory.create(mDirectoryIdGenerator.getNewDirectoryId(journalAppender),
            currentInodeDirectory.getId(), pathComponents[k], missingDirOptions);
        // Lock the newly created inode before subsequent operations, and add it to the lock group.
        lockList.lockWriteAndCheckNameAndParent(dir, currentInodeDirectory, pathComponents[k]);

        if (!currentInodeDirectory.addChild(dir)) {
          // The child directory inode already exists. Get the existing child inode.
          lockList.unlockLast();

          dir = (InodeDirectory) currentInodeDirectory.getChildReadLock(pathComponents[k], lockList);
          if (dir == null) {
            // Could not get the child inode. Continue and try again.
            continue;
          }
        } else {
          // Successfully added the child, while holding the write lock.
          dir.setPinned(currentInodeDirectory.isPinned());
          currentInodeDirectory.addChild(dir);
          currentInodeDirectory.setLastModificationTimeMs(options.getOperationTimeMs());
          if (options.isPersisted()) {
            // Do not journal the persist entry, since a creation entry will be journaled instead.
            syncPersistDirectory(dir, null);
          }
          // Journal the new inode.
          journalAppender.append(dir.toJournalEntry());

          // After creation and journaling, downgrade to a read lock.
          lockList.unlockLast();

          dir =
              (InodeDirectory) currentInodeDirectory.getChildReadLock(pathComponents[k], lockList);
          if (dir == null) {
            // Could not get the child inode. Continue and try again.
            continue;
          }
        }
      }

      createdInodes.add(dir);
      mInodes.add(dir);
      currentInodeDirectory = dir;
    }

    // Create the final path component. First we need to make sure that there isn't already a file
    // here with that name. If there is an existing file that is a directory and we're creating a
    // directory, update persistence property of the directories if needed, otherwise, throw
    // FileAlreadyExistsException unless options.allowExists is true.
    // TODO(gpang): does this have to be a write lock?
    Inode<?> lastInode = currentInodeDirectory.getChildWriteLock(name, lockList);
    if (lastInode != null) {
      if (lastInode.isDirectory() && options instanceof CreateDirectoryOptions && !lastInode
          .isPersisted() && options.isPersisted()) {
        // The final path component already exists and is not persisted, so it should be added
        // to the non-persisted Inodes of traversalResult.
        existingNonPersisted.add(lastInode);
        toPersistDirectories.add(lastInode);
      } else if (!lastInode.isDirectory() || !(options instanceof CreateDirectoryOptions
          && ((CreateDirectoryOptions) options).isAllowExists())) {
        String errorMessage = ExceptionMessage.FILE_ALREADY_EXISTS.getMessage(path);
        LOG.error(errorMessage);
        throw new FileAlreadyExistsException(errorMessage);
      }
    } else {
      if (options instanceof CreateDirectoryOptions) {
        CreateDirectoryOptions directoryOptions = (CreateDirectoryOptions) options;
        lastInode = InodeDirectory.create(mDirectoryIdGenerator.getNewDirectoryId(journalAppender),
            currentInodeDirectory.getId(), name, directoryOptions);
        // Lock the created inode before subsequent operations, and add it to the lock group.
        lockList.lockWriteAndCheckNameAndParent(lastInode, currentInodeDirectory, name);
        if (directoryOptions.isPersisted()) {
          toPersistDirectories.add(lastInode);
        }
        lastInode.setPinned(currentInodeDirectory.isPinned());
      } else if (options instanceof CreateFileOptions) {
        CreateFileOptions fileOptions = (CreateFileOptions) options;
        lastInode = InodeFile.create(mContainerIdGenerator.getNewContainerId(),
            currentInodeDirectory.getId(), name, System.currentTimeMillis(), fileOptions);
        // Lock the created inode before subsequent operations, and add it to the lock group.
        lockList.lockWriteAndCheckNameAndParent(lastInode, currentInodeDirectory, name);
        if (currentInodeDirectory.isPinned()) {
          // Update set of pinned file ids.
          mPinnedInodeFileIds.add(lastInode.getId());
        }
        lastInode.setPinned(currentInodeDirectory.isPinned());
      }

      // Journal the new inode.
      journalAppender.append(lastInode.toJournalEntry());

      createdInodes.add(lastInode);
      mInodes.add(lastInode);
      // TODO(gpang): handle the case where this returns false
      currentInodeDirectory.addChild(lastInode);
      currentInodeDirectory.setLastModificationTimeMs(options.getOperationTimeMs());
    }

    // Persists all directories one by one rather than recursively creating necessary parent
    // directories, because different ufs may have different semantics in the ACL permission of
    // those recursively created directories. Even if the directory already exists in the ufs,
    // we mark it as persisted.
    for (Inode<?> inode : toPersistDirectories) {
      MountTable.Resolution resolution = mMountTable.resolve(getPath(inode));
      String ufsUri = resolution.getUri().toString();
      UnderFileSystem ufs = resolution.getUfs();
      MkdirsOptions mkdirsOptions =
          MkdirsOptions.defaults().setCreateParent(false).setOwner(inode.getOwner())
              .setGroup(inode.getGroup()).setMode(new Mode(inode.getMode()));
      if (ufs.isDirectory(ufsUri) || ufs.mkdirs(ufsUri, mkdirsOptions)) {
        inode.setPersistenceState(PersistenceState.PERSISTED);
      }
    }

    // Extend the inodePath with the created inodes.
    extensibleInodePath.getInodes().addAll(createdInodes);
    LOG.debug("createFile: File Created: {} parent: {}", lastInode, currentInodeDirectory);
    return new CreatePathResult(modifiedInodes, createdInodes, existingNonPersisted);
  }