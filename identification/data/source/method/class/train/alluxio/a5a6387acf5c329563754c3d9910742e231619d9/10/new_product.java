public List<Inode> createPath(RpcContext rpcContext, LockedInodePath inodePath,
      CreatePathContext<?, ?> context) throws FileAlreadyExistsException, BlockInfoException,
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
      if (context instanceof CreateDirectoryContext
          && ((CreateDirectoryContext) context).getOptions().getAllowExists()) {
        return new ArrayList<>();
      } else {
        throw new FileAlreadyExistsException(path);
      }
    }

    if (context instanceof CreateFileContext) {
      CreateFileContext fileContext = (CreateFileContext) context;
      if (fileContext.getOptions().getBlockSizeBytes() < 1) {
        throw new BlockInfoException(
            "Invalid block size " + fileContext.getOptions().getBlockSizeBytes());
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
      if (!context.isRecursive()) {
        throw new FileDoesNotExistException(String.format(
            "File %s creation failed. Component %d(%s) does not exist",
            path, pathIndex, pathComponents[pathIndex]));
      }
    }
    // The ancestor inode (parent or ancestor) of the target path.
    Inode ancestorInode = inodePath.getAncestorInode();
    if (!ancestorInode.isDirectory()) {
      throw new InvalidPathException("Could not traverse to parent directory of path " + path
          + ". Component " + pathComponents[pathIndex - 1] + " is not a directory.");
    }
    InodeDirectoryView currentInodeDirectory = ancestorInode.asDirectory();

    List<Inode> createdInodes = new ArrayList<>();
    if (context.isPersisted()) {
      // Synchronously persist directories. These inodes are already READ locked.
      for (Inode inode : inodePath.getInodeList()) {
        if (!inode.isPersisted()) {
          // This cast is safe because we've already verified that the file inode doesn't exist.
          syncPersistExistingDirectory(rpcContext, inode.asDirectory());
        }
      }
    }
    if ((pathIndex < (pathComponents.length - 1)
        || !mInodeStore.getChild(currentInodeDirectory, name).isPresent())
        && context.getOperationTimeMs() > currentInodeDirectory.getLastModificationTimeMs()) {
      // (1) There are components in parent paths that need to be created. Or
      // (2) The last component of the path needs to be created.
      // In these two cases, the last traversed Inode will be modified if the new timestamp is after
      // the existing last modified time.
      long currentId = currentInodeDirectory.getId();
      try (LockResource lr = mInodeLockManager.lockUpdate(currentId)) {
        long updatedLastModified = mInodeStore.get(currentId).get().getLastModificationTimeMs();
        if (updatedLastModified < context.getOperationTimeMs()) {
          mState.applyAndJournal(rpcContext, UpdateInodeEntry.newBuilder()
              .setId(currentId)
              .setLastModificationTimeMs(context.getOperationTimeMs())
              .build());
        }
      }
    }

    // Fill in the ancestor directories that were missing.
    // NOTE, we set the mode of missing ancestor directories to be the default value, rather
    // than inheriting the option of the final file to create, because it may not have
    // "execute" permission.
    CreateDirectoryContext missingDirContext = CreateDirectoryContext.defaults();
    missingDirContext.getOptions().setCommonOptions(FileSystemMasterCommonPOptions.newBuilder()
        .setTtl(context.getTtl()).setTtlAction(context.getTtlAction()));
    missingDirContext.setPersisted(context.isPersisted());
    missingDirContext.setOperationTimeMs(context.getOperationTimeMs());
    missingDirContext.setMountPoint(false);
    missingDirContext.setOwner(context.getOwner());
    missingDirContext.setGroup(context.getGroup());
    for (int k = pathIndex; k < (pathComponents.length - 1); k++) {
      MutableInodeDirectory newDir = MutableInodeDirectory.create(
          mDirectoryIdGenerator.getNewDirectoryId(rpcContext.getJournalContext()),
          currentInodeDirectory.getId(), pathComponents[k], missingDirContext);

      newDir.setPinned(currentInodeDirectory.isPinned());

      // if the parent has default ACL, copy that default ACL as the new directory's default
      // and access acl, ANDed with the umask
      // if it is part of a metadata load operation, we ignore the umask and simply inherit
      // the default ACL as the directory's new default and access ACL
      short mode = context.isMetadataLoad() ? Mode.createFullAccess().toShort()
          : newDir.getMode();
      DefaultAccessControlList dAcl = currentInodeDirectory.getDefaultACL();
      if (!dAcl.isEmpty()) {
        Pair<AccessControlList, DefaultAccessControlList> pair =
            dAcl.generateChildDirACL(mode);
        newDir.setInternalAcl(pair.getFirst());
        newDir.setDefaultACL(pair.getSecond());
      }
      mState.applyAndJournal(rpcContext, newDir);

      inodePath.addNextInode(Inode.wrap(newDir));

      // Persist the directory *after* it exists in the inode tree. This prevents multiple
      // concurrent creates from trying to persist the same directory name.
      if (context.isPersisted()) {
        syncPersistExistingDirectory(rpcContext, newDir);
      }

      createdInodes.add(Inode.wrap(newDir));
      currentInodeDirectory = newDir;
    }

    // Create the final path component.
    MutableInode<?> newInode;
    // create the new inode, with a write lock
    if (context instanceof CreateDirectoryContext) {
      CreateDirectoryContext directoryContext = (CreateDirectoryContext) context;
      MutableInodeDirectory newDir = MutableInodeDirectory.create(
          mDirectoryIdGenerator.getNewDirectoryId(rpcContext.getJournalContext()),
          currentInodeDirectory.getId(), name, directoryContext);

      // if the parent has default ACL, take the default ACL ANDed with the umask as the new
      // directory's default and access acl
      // When it is a metadata load operation, do not take the umask into account
      short mode = context.isMetadataLoad() ? Mode.createFullAccess().toShort()
          : newDir.getMode();
      DefaultAccessControlList dAcl = currentInodeDirectory.getDefaultACL();
      if (!dAcl.isEmpty()) {
        Pair<AccessControlList, DefaultAccessControlList> pair =
            dAcl.generateChildDirACL(mode);
        newDir.setInternalAcl(pair.getFirst());
        newDir.setDefaultACL(pair.getSecond());
      }

      if (directoryContext.isPersisted()) {
        // Do not journal the persist entry, since a creation entry will be journaled instead.
        if (context.isMetadataLoad()) {
          // if we are creating the file as a result of loading metadata, the newDir is already
          // persisted, and we got the permissions info from the ufs.
          newDir.setOwner(context.getOwner())
              .setGroup(context.getGroup())
              .setMode(context.getMode().toShort());

          Long lastModificationTime = context.getOperationTimeMs();
          if (lastModificationTime != null) {
            newDir.setLastModificationTimeMs(lastModificationTime, true);
          }
          newDir.setPersistenceState(PersistenceState.PERSISTED);
        } else {
          syncPersistNewDirectory(newDir);
        }
      }
      newInode = newDir;
    } else if (context instanceof CreateFileContext) {
      CreateFileContext fileContext = (CreateFileContext) context;
      MutableInodeFile newFile = MutableInodeFile.create(mContainerIdGenerator.getNewContainerId(),
          currentInodeDirectory.getId(), name, System.currentTimeMillis(), fileContext);
      // if the parent has a default ACL, copy that default ACL ANDed with the umask as the new
      // file's access ACL.
      // If it is a metadata load operation, do not consider the umask.
      DefaultAccessControlList dAcl = currentInodeDirectory.getDefaultACL();
      short mode = context.isMetadataLoad() ? Mode.createFullAccess().toShort() : newFile.getMode();
      if (!dAcl.isEmpty()) {
        AccessControlList acl = dAcl.generateChildFileACL(mode);
        newFile.setInternalAcl(acl);
      }

      if (fileContext.isCacheable()) {
        newFile.setCacheable(true);
      }
      newInode = newFile;
    } else {
      throw new IllegalStateException(String.format("Unrecognized create options: %s", context));
    }
    newInode.setPinned(currentInodeDirectory.isPinned());

    mState.applyAndJournal(rpcContext, newInode);
    Inode inode = Inode.wrap(newInode);
    inodePath.addNextInode(inode);
    createdInodes.add(inode);
    LOG.debug("createFile: File Created: {} parent: {}", newInode, currentInodeDirectory);
    return createdInodes;
  }