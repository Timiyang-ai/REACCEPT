public CreatePathResult createPath(AlluxioURI path, CreatePathOptions options)
      throws FileAlreadyExistsException, BlockInfoException, InvalidPathException, IOException {
    if (path.isRoot()) {
      LOG.info("FileAlreadyExistsException: {}", path);
      throw new FileAlreadyExistsException(path.toString());
    }
    if (!options.isDirectory()) {
      CreateFileOptions fileOptions = (CreateFileOptions) options;
      if (fileOptions.getBlockSizeBytes() < 1) {
        throw new BlockInfoException("Invalid block size " + fileOptions.getBlockSizeBytes());
      }
    }

    LOG.debug("createPath {}", FormatUtils.parametersToString(path));

    String[] pathComponents = PathUtils.getPathComponents(path.getPath());
    String name = path.getName();

    String[] parentPath = new String[pathComponents.length - 1];
    System.arraycopy(pathComponents, 0, parentPath, 0, parentPath.length);

    TraversalResult traversalResult = traverseToInode(parentPath, options.isPersisted());
    // pathIndex is the index into pathComponents where we start filling in the path from the inode.
    int pathIndex = parentPath.length;
    if (!traversalResult.isFound()) {
      // Then the path component at errorInd k doesn't exist. If it's not recursive, we throw an
      // exception here. Otherwise we add the remaining path components to the list of components
      // to create.
      if (!options.isRecursive()) {
        final String msg = new StringBuilder().append("File ").append(path)
            .append(" creation failed. Component ")
            .append(traversalResult.getNonexistentPathIndex()).append("(")
            .append(parentPath[traversalResult.getNonexistentPathIndex()])
            .append(") does not exist").toString();
        LOG.info("InvalidPathException: {}", msg);
        throw new InvalidPathException(msg);
      } else {
        // We will start filling at the index of the non-existing step found by the traversal.
        pathIndex = traversalResult.getNonexistentPathIndex();
      }
    }

    if (!traversalResult.getInode().isDirectory()) {
      throw new InvalidPathException("Could not traverse to parent directory of path " + path
          + ". Component " + pathComponents[pathIndex - 1] + " is not a directory.");
    }
    InodeDirectory currentInodeDirectory = (InodeDirectory) traversalResult.getInode();
    List<Inode> createdInodes = Lists.newArrayList();
    List<Inode> modifiedInodes = Lists.newArrayList();
    // Directory persistence will not happen until the end of this method.
    List<Inode> toPersistDirectories = Lists.newArrayList(traversalResult.getNonPersisted());
    if (pathIndex < parentPath.length || currentInodeDirectory.getChild(name) == null) {
      // (1) There are components in parent paths that need to be created. Or
      // (2) The last component of the path needs to be created.
      // In these two cases, the last traversed Inode will be modified.
      modifiedInodes.add(currentInodeDirectory);
    }

    // Fill in the directories that were missing.
    for (int k = pathIndex; k < parentPath.length; k++) {
      Inode dir =
          new InodeDirectory(mDirectoryIdGenerator.getNewDirectoryId()).setName(pathComponents[k])
              .setParentId(currentInodeDirectory.getId()).setPersistenceState(
              options.isPersisted() ? PersistenceState.PERSISTED : PersistenceState.NOT_PERSISTED)
              .setPermissionStatus(options.getPermissionStatus());
      dir.setPinned(currentInodeDirectory.isPinned());
      currentInodeDirectory.addChild(dir);
      currentInodeDirectory.setLastModificationTimeMs(options.getOperationTimeMs());
      if (options.isPersisted()) {
        toPersistDirectories.add(dir);
      }
      createdInodes.add(dir);
      mInodes.add(dir);
      currentInodeDirectory = (InodeDirectory) dir;
    }

    // Create the final path component. First we need to make sure that there isn't already a file
    // here with that name. If there is an existing file that is a directory and we're creating a
    // directory, update persistence property of the directories if needed, otherwise, throw
    // FileAlreadyExistsException unless options.allowExists is true.
    Inode lastInode = currentInodeDirectory.getChild(name);
    if (lastInode != null) {
      if (lastInode.isDirectory() && options.isDirectory() && !lastInode.isPersisted()
          && options.isPersisted()) {
        // The final path component already exists and is not persisted, so it should be added
        // to the non-persisted Inodes of traversalResult.
        traversalResult.getNonPersisted().add(lastInode);
        toPersistDirectories.add(lastInode);
      } else if (!(lastInode.isDirectory() && ((CreateDirectoryOptions) options).isAllowExists())) {
        LOG.info(ExceptionMessage.FILE_ALREADY_EXISTS.getMessage(path));
        throw new FileAlreadyExistsException(ExceptionMessage.FILE_ALREADY_EXISTS.getMessage(path));
      }
    } else {
      if (options.isDirectory()) {
        CreateDirectoryOptions directoryOptions = (CreateDirectoryOptions) options;
        lastInode = new InodeDirectory(mDirectoryIdGenerator.getNewDirectoryId()).setName(name)
            .setParentId(currentInodeDirectory.getId())
            .setPermissionStatus(directoryOptions.getPermissionStatus())
            .setMountPoint(directoryOptions.isMountPoint());
        if (directoryOptions.isPersisted()) {
          toPersistDirectories.add(lastInode);
        }
      } else {
        CreateFileOptions fileOptions = (CreateFileOptions) options;
        lastInode = new InodeFile(mContainerIdGenerator.getNewContainerId())
            .setBlockSizeBytes(fileOptions.getBlockSizeBytes()).setTtl(fileOptions.getTtl())
            .setName(name).setParentId(currentInodeDirectory.getId()).setPersistenceState(
                fileOptions.isPersisted() ? PersistenceState.PERSISTED :
                    PersistenceState.NOT_PERSISTED)
            .setPermissionStatus(fileOptions.getPermissionStatus());
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
      Inode lastToPersistInode = toPersistDirectories.get(toPersistDirectories.size() - 1);
      String ufsPath = mMountTable.resolve(getPath(lastToPersistInode)).toString();
      UnderFileSystem ufs = UnderFileSystem.get(ufsPath, MasterContext.getConf());
      // Persists only the last directory, recursively creating necessary parent directories. Even
      // if the directory already exists in the ufs, we mark it as persisted.
      if (ufs.exists(ufsPath) || ufs.mkdirs(ufsPath, true)) {
        for (Inode inode : toPersistDirectories) {
          inode.setPersistenceState(PersistenceState.PERSISTED);
        }
      }
    }

    LOG.debug("createFile: File Created: {} parent: ", lastInode, currentInodeDirectory);
    return new CreatePathResult(modifiedInodes, createdInodes, traversalResult.getNonPersisted());
  }