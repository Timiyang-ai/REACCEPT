public CreatePathResult createPath(RpcContext rpcContext, LockedInodePath inodePath,
      CreatePathOptions<?> options) throws FileAlreadyExistsException, BlockInfoException,
      InvalidPathException, IOException, FileDoesNotExistException {
    Preconditions.checkState(inodePath.getLockPattern() == LockPattern.WRITE_EDGE);

    // TODO(gpang): consider splitting this into createFilePath and createDirectoryPath, with a
    // helper method for the shared logic.
    AlluxioURI path = inodePath.getUri();
    if (path.isRoot()) {
      String errorMessage = ExceptionMessage.FILE_ALREADY_EXISTS.getMessage(path);
      LOG.error(errorMessage);
      throw new FileAlreadyExistsException(errorMessage);
    }
    if (inodePath.fullPathExists()) {
      if (options instanceof CreateDirectoryOptions
          && ((CreateDirectoryOptions) options).isAllowExists()) {
        return new CreatePathResult(new ArrayList<>(), new ArrayList<>());
      } else {
        throw new FileAlreadyExistsException(path);
      }
    }

    if (options instanceof CreateFileOptions) {
      CreateFileOptions fileOptions = (CreateFileOptions) options;
      if (fileOptions.getBlockSizeBytes() < 1) {
        throw new BlockInfoException("Invalid block size " + fileOptions.getBlockSizeBytes());
      }
    }

    LOG.debug("createPath {}", path);

    String[] pathComponents = inodePath.mPathComponents;
    String name = path.getName();

    // pathIndex is the index into pathComponents where we start filling in the path from the inode.
    int pathIndex = inodePath.getExistingInodeCount();
    if (pathIndex < pathComponents.length - 1) {
      // The immediate parent was not found. If it's not recursive, we throw an exception here.
      // Otherwise we add the remaining path components to the list of components to create.
      if (!options.isRecursive()) {
        throw new FileDoesNotExistException(String.format(
            "File %s creation failed. Component %d(%s) does not exist",
            path, pathIndex, pathComponents[pathIndex]));
      }
    }
    // The ancestor inode (parent or ancestor) of the target path.
    InodeView ancestorInode = inodePath.getAncestorInode();
    if (!ancestorInode.isDirectory()) {
      throw new InvalidPathException("Could not traverse to parent directory of path " + path
          + ". Component " + pathComponents[pathIndex - 1] + " is not a directory.");
    }
    InodeDirectoryView currentInodeDirectory = (InodeDirectoryView) ancestorInode;

    List<InodeView> createdInodes = new ArrayList<>();
    List<InodeView> modifiedInodes = new ArrayList<>();
    if (options.isPersisted()) {
      // Synchronously persist directories. These inodes are already READ locked.
      for (InodeView inode : inodePath.getInodeList()) {
        if (!inode.isPersisted()) {
          // This cast is safe because we've already verified that the file inode doesn't exist.
          syncPersistExistingDirectory(rpcContext, (InodeDirectoryView) inode);
        }
      }
    }
    if ((pathIndex < (pathComponents.length - 1) || currentInodeDirectory.getChild(name) == null)
        && options.getOperationTimeMs() > currentInodeDirectory.getLastModificationTimeMs()) {
      // (1) There are components in parent paths that need to be created. Or
      // (2) The last component of the path needs to be created.
      // In these two cases, the last traversed Inode will be modified if the new timestamp is after
      // the existing last modified time.
      mState.applyAndJournal(rpcContext, UpdateInodeEntry.newBuilder()
          .setId(currentInodeDirectory.getId())
          .setLastModificationTimeMs(options.getOperationTimeMs())
          .build());
      modifiedInodes.add(currentInodeDirectory);
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
      InodeDirectory newDir = InodeDirectory.create(
          mDirectoryIdGenerator.getNewDirectoryId(rpcContext.getJournalContext()),
          currentInodeDirectory.getId(), pathComponents[k], missingDirOptions);

      newDir.setPinned(currentInodeDirectory.isPinned());

      // if the parent has default ACL, copy that default ACL as the new directory's default
      // and access acl, ANDed with the umask
      // if it is part of a metadata load operation, we ignore the umask and simply inherit
      // the default ACL as the directory's new default and access ACL
      short mode = options.isMetadataLoad() ? Mode.createFullAccess().toShort()
          : newDir.getMode();
      DefaultAccessControlList dAcl = currentInodeDirectory.getDefaultACL();
      if (!dAcl.isEmpty()) {
        Pair<AccessControlList, DefaultAccessControlList> pair =
            dAcl.generateChildDirACL(mode);
        newDir.setInternalAcl(pair.getFirst());
        newDir.setDefaultACL(pair.getSecond());
      }
      mState.applyAndJournal(rpcContext, newDir);

      inodePath.addNextInode(newDir);

      // Persist the directory *after* it exists in the inode tree. This prevents multiple
      // concurrent creates from trying to persist the same directory name.
      if (options.isPersisted()) {
        syncPersistExistingDirectory(rpcContext, newDir);
      }

      createdInodes.add(newDir);
      currentInodeDirectory = newDir;
    }

    // Create the final path component.
    Inode<?> newInode;
    // create the new inode, with a write lock
    if (options instanceof CreateDirectoryOptions) {
      CreateDirectoryOptions directoryOptions = (CreateDirectoryOptions) options;
      InodeDirectory newDir = InodeDirectory.create(
          mDirectoryIdGenerator.getNewDirectoryId(rpcContext.getJournalContext()),
          currentInodeDirectory.getId(), name, directoryOptions);

      // if the parent has default ACL, take the default ACL ANDed with the umask as the new
      // directory's default and access acl
      // When it is a metadata load operation, do not take the umask into account
      short mode = options.isMetadataLoad() ? Mode.createFullAccess().toShort()
          : newDir.getMode();
      DefaultAccessControlList dAcl = currentInodeDirectory.getDefaultACL();
      if (!dAcl.isEmpty()) {
        Pair<AccessControlList, DefaultAccessControlList> pair =
            dAcl.generateChildDirACL(mode);
        newDir.setInternalAcl(pair.getFirst());
        newDir.setDefaultACL(pair.getSecond());
      }

      if (directoryOptions.isPersisted()) {
        // Do not journal the persist entry, since a creation entry will be journaled instead.
        if (options.isMetadataLoad()) {
          // if we are creating the file as a result of loading metadata, the newDir is already
          // persisted, and we got the permissions info from the ufs.
          newDir.setOwner(options.getOwner())
              .setGroup(options.getGroup())
              .setMode(options.getMode().toShort());

          Long lastModificationTime = options.getOperationTimeMs();
          if (lastModificationTime != null) {
            newDir.setLastModificationTimeMs(lastModificationTime, true);
          }
          newDir.setPersistenceState(PersistenceState.PERSISTED);
        } else {
          syncPersistNewDirectory(newDir);
        }
      }
      newInode = newDir;
    } else if (options instanceof CreateFileOptions) {
      CreateFileOptions fileOptions = (CreateFileOptions) options;
      InodeFile newFile = InodeFile.create(mContainerIdGenerator.getNewContainerId(),
          currentInodeDirectory.getId(), name, System.currentTimeMillis(), fileOptions);

      // if the parent has a default ACL, copy that default ACL ANDed with the umask as the new
      // file's access ACL.
      // If it is a metadata load operation, do not consider the umask.
      DefaultAccessControlList dAcl = currentInodeDirectory.getDefaultACL();
      short mode = options.isMetadataLoad() ? Mode.createFullAccess().toShort()
          : newFile.getMode();
      if (!dAcl.isEmpty()) {
        AccessControlList acl = dAcl.generateChildFileACL(mode);
        newFile.setInternalAcl(acl);
      }

      if (fileOptions.isCacheable()) {
        newFile.setCacheable(true);
      }
      newInode = newFile;
    } else {
      throw new IllegalStateException(String.format("Unrecognized create options: %s", options));
    }
    newInode.setPinned(currentInodeDirectory.isPinned());

    mState.applyAndJournal(rpcContext, newInode);
    inodePath.addNextInode(newInode);

    createdInodes.add(newInode);
    LOG.debug("createFile: File Created: {} parent: {}", newInode, currentInodeDirectory);
    return new CreatePathResult(modifiedInodes, createdInodes);
  }