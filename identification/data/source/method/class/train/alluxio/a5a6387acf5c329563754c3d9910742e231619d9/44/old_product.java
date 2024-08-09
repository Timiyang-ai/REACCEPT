public CreatePathResult createPath(TachyonURI path, CreatePathOptions options)
      throws FileAlreadyExistsException, BlockInfoException, InvalidPathException, IOException {
    if (path.isRoot()) {
      LOG.info("FileAlreadyExistsException: {}", path);
      throw new FileAlreadyExistsException(path.toString());
    }
    if (!options.isDirectory() && options.getBlockSizeBytes() < 1) {
      throw new BlockInfoException("Invalid block size " + options.getBlockSizeBytes());
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
    for (int k = pathIndex; k < parentPath.length; k ++) {
      Inode dir =
          new InodeDirectory.Builder().setName(pathComponents[k])
              .setId(mDirectoryIdGenerator.getNewDirectoryId())
              .setParentId(currentInodeDirectory.getId())
              .setPersisted(options.isPersisted())
              .setCreationTimeMs(options.getOperationTimeMs()).build();
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
    // directory, update persistence property of the directories if needed, otherwise, nothing needs
    // to be done.
    Inode lastInode = currentInodeDirectory.getChild(name);
    if (lastInode != null) {
      if (lastInode.isDirectory() && options.isDirectory()) {
        if (!lastInode.isPersisted() && options.isPersisted()) {
          // The final path component already exists and is not persisted, so it should be added
          // to the non-persisted Inodes of traversalResult.
          traversalResult.getNonPersisted().add(lastInode);
          toPersistDirectories.add(lastInode);
        }
      } else {
        LOG.info("FileAlreadyExistsException: {}", path);
        throw new FileAlreadyExistsException(path.toString());
      }
    } else {
      if (options.isDirectory()) {
        lastInode =
            new InodeDirectory.Builder().setName(name)
                .setId(mDirectoryIdGenerator.getNewDirectoryId())
                .setParentId(currentInodeDirectory.getId()).build();
        if (options.isPersisted()) {
          toPersistDirectories.add(lastInode);
        }
      } else {
        lastInode =
            new InodeFile.Builder().setBlockContainerId(mContainerIdGenerator.getNewContainerId())
                .setBlockSizeBytes(options.getBlockSizeBytes()).setTTL(options.getTTL())
                .setName(name).setParentId(currentInodeDirectory.getId())
                .setPersisted(options.isPersisted()).setCreationTimeMs(options.getOperationTimeMs())
                .build();
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
      // Persists only the last directory, recursively creating necessary parent directories.
      if (ufs.mkdirs(ufsPath, true)) {
        for (Inode inode : toPersistDirectories) {
          inode.setPersisted(true);
        }
      }
    }

    LOG.debug("createFile: File Created: {} parent: ", lastInode, currentInodeDirectory);
    return new CreatePathResult(modifiedInodes, createdInodes, traversalResult.getNonPersisted());
  }