public CreatePathResult createPath(InodePath inodePath, CreatePathOptions<?> options)
      throws FileAlreadyExistsException, BlockInfoException, InvalidPathException, IOException,
      FileDoesNotExistException {
    AlluxioURI path = inodePath.getUri();
    if (path.isRoot()) {
      LOG.info(ExceptionMessage.FILE_ALREADY_EXISTS.getMessage(path));
      throw new FileAlreadyExistsException(ExceptionMessage.FILE_ALREADY_EXISTS.getMessage(path));
    }
    if (options instanceof CreateFileOptions) {
      CreateFileOptions fileOptions = (CreateFileOptions) options;
      if (fileOptions.getBlockSizeBytes() < 1) {
        throw new BlockInfoException("Invalid block size " + fileOptions.getBlockSizeBytes());
      }
    }

    if (!(inodePath instanceof ExtensibleInodePath)) {
      throw new InvalidPathException("Not an ExtensibleInodePath: " + inodePath.getUri());
    }

    LOG.debug("createPath {}", path);

    // TODO(gpang): lock the last inode(s) with write lock.
    TraversalResult traversalResult = traverseToInode(inodePath, LockMode.WRITE);
    InodeLockGroup lockGroup = traversalResult.getInodeLockGroup();

    ExtensibleInodePath extensibleInodePath = (ExtensibleInodePath) inodePath;
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
        LOG.info("FileDoesNotExistException: {}", msg);
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

    List<Inode<?>> createdInodes = Lists.newArrayList();
    List<Inode<?>> modifiedInodes = Lists.newArrayList();
    // These are inodes that already exist, that should be journaled as persisted.
    List<Inode<?>> existingNonPersisted = Lists.newArrayList();
    // These are inodes to mark as persisted at the end of this method.
    List<Inode<?>> toPersistDirectories = Lists.newArrayList();
    if (options.isPersisted()) {
      // Directory persistence will not happen until the end of this method.
      toPersistDirectories.addAll(traversalResult.getNonPersisted());
      existingNonPersisted.addAll(traversalResult.getNonPersisted());
    }
    if (pathIndex < (pathComponents.length - 1) || currentInodeDirectory.getChild(name) == null) {
      // (1) There are components in parent paths that need to be created. Or
      // (2) The last component of the path needs to be created.
      // In these two cases, the last traversed Inode will be modified.
      modifiedInodes.add(currentInodeDirectory);
    }

    // Fill in the ancestor directories that were missing.
    CreateDirectoryOptions missingDirOptions = CreateDirectoryOptions.defaults()
        .setMountPoint(false)
        .setPersisted(options.isPersisted())
        .setPermissionStatus(options.getPermissionStatus());
    for (int k = pathIndex; k < (pathComponents.length - 1); k++) {
      InodeDirectory dir =
          InodeDirectory.create(mDirectoryIdGenerator.getNewDirectoryId(),
              currentInodeDirectory.getId(), pathComponents[k], missingDirOptions);
      // Lock the newly created inode before subsequent operations, and add it to the lock group.
//        lockGroup.lockWrite(dir);

      dir.setPinned(currentInodeDirectory.isPinned());
      currentInodeDirectory.addChild(dir);
      currentInodeDirectory.setLastModificationTimeMs(options.getOperationTimeMs());
      if (options.isPersisted()) {
        toPersistDirectories.add(dir);
      }
      createdInodes.add(dir);
      mInodes.add(dir);
      currentInodeDirectory = dir;
    }

    // Create the final path component. First we need to make sure that there isn't already a file
    // here with that name. If there is an existing file that is a directory and we're creating a
    // directory, update persistence property of the directories if needed, otherwise, throw
    // FileAlreadyExistsException unless options.allowExists is true.
    Inode<?> lastInode = currentInodeDirectory.getChild(name);
    if (lastInode != null) {
      // Lock the last inode before subsequent operations, and add it to the lock group.
//        lockGroup.lockWrite(lastInode);

      if (lastInode.isDirectory() && options instanceof CreateDirectoryOptions && !lastInode
          .isPersisted() && options.isPersisted()) {
        // The final path component already exists and is not persisted, so it should be added
        // to the non-persisted Inodes of traversalResult.
        existingNonPersisted.add(lastInode);
        toPersistDirectories.add(lastInode);
      } else if (!lastInode.isDirectory() || !(options instanceof CreateDirectoryOptions
          && ((CreateDirectoryOptions) options).isAllowExists())) {
        LOG.info(ExceptionMessage.FILE_ALREADY_EXISTS.getMessage(path));
        throw new FileAlreadyExistsException(
            ExceptionMessage.FILE_ALREADY_EXISTS.getMessage(path));
      }
    } else {
      if (options instanceof CreateDirectoryOptions) {
        CreateDirectoryOptions directoryOptions = (CreateDirectoryOptions) options;
        lastInode = InodeDirectory.create(mDirectoryIdGenerator.getNewDirectoryId(),
            currentInodeDirectory.getId(), name, directoryOptions);
        // Lock the created inode before subsequent operations, and add it to the lock group.
//          lockGroup.lockWrite(lastInode);
        if (directoryOptions.isPersisted()) {
          toPersistDirectories.add(lastInode);
        }
      }
      if (options instanceof CreateFileOptions) {
        CreateFileOptions fileOptions = (CreateFileOptions) options;
        lastInode = InodeFile.create(mContainerIdGenerator.getNewContainerId(),
            currentInodeDirectory.getId(), name, fileOptions);
        // Lock the created inode before subsequent operations, and add it to the lock group.
//          lockGroup.lockWrite(lastInode);
        if (currentInodeDirectory.isPinned()) {
          // Update set of pinned file ids.
          mPinnedInodeFileIds.add(lastInode.getId());
        }
      }
      lastInode.setPinned(currentInodeDirectory.isPinned());

      createdInodes.add(lastInode);
      mInodes.add(lastInode);
      currentInodeDirectory.addChild(lastInode);
      currentInodeDirectory.setLastModificationTimeMs(options.getOperationTimeMs());
    }

    if (toPersistDirectories.size() > 0) {
      Inode<?> lastToPersistInode = toPersistDirectories.get(toPersistDirectories.size() - 1);
      MountTable.Resolution resolution = mMountTable.resolve(getPath(lastToPersistInode));
      String ufsUri = resolution.getUri().toString();
      UnderFileSystem ufs = resolution.getUfs();
      // Persists only the last directory, recursively creating necessary parent directories. Even
      // if the directory already exists in the ufs, we mark it as persisted.
      if (ufs.exists(ufsUri) || ufs.mkdirs(ufsUri, true)) {
        for (Inode<?> inode : toPersistDirectories) {
          inode.setPersistenceState(PersistenceState.PERSISTED);
        }
      }
    }

    LOG.debug("createFile: File Created: {} parent: ", lastInode, currentInodeDirectory);
    return new CreatePathResult(modifiedInodes, createdInodes, existingNonPersisted);
  }