public CreatePathResult createPath(TachyonURI path, CreatePathOptions options)
      throws FileAlreadyExistsException, BlockInfoException, InvalidPathException {
    if (path.isRoot()) {
      LOG.info("FileAlreadyExistsException: " + path);
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

    TraversalResult traversalResult = traverseToInode(parentPath);
    // pathIndex is the index into pathComponents where we start filling in the path from the inode.
    int pathIndex = parentPath.length;
    if (!traversalResult.isFound()) {
      // Then the path component at errorInd k doesn't exist. If it's not recursive, we throw an
      // exception here. Otherwise we add the remaining path components to the list of components
      // to create.
      if (!options.isRecursive()) {
        final String msg = "File " + path + " creation failed. Component "
            + traversalResult.getNonexistentPathIndex() + "("
            + parentPath[traversalResult.getNonexistentPathIndex()] + ") does not exist";
        LOG.info("InvalidPathException: " + msg);
        throw new InvalidPathException(msg);
      } else {
        // We will start filling at the index of the non-existing step found by the traveral
        pathIndex = traversalResult.getNonexistentPathIndex();
      }
    }

    if (!traversalResult.getInode().isDirectory()) {
      throw new InvalidPathException("Could not traverse to parent directory of path " + path
          + ". Component " + pathComponents[pathIndex - 1] + " is not a directory.");
    }
    InodeDirectory currentInodeDirectory = (InodeDirectory) traversalResult.getInode();
    List<Inode> modifiedInodes = Lists.newArrayList();
    List<Inode> createdInodes = Lists.newArrayList();
    // Add the last found inode, since the modification time will be updated when descendants are
    // created.
    modifiedInodes.add(currentInodeDirectory);
    // Fill in the directories that were missing.
    for (int k = pathIndex; k < parentPath.length; k ++) {
      Inode dir = new InodeDirectory(pathComponents[k], mDirectoryIdGenerator.getNewDirectoryId(),
          currentInodeDirectory.getId(), options.getOperationTimeMs());
      dir.setPinned(currentInodeDirectory.isPinned());
      currentInodeDirectory.addChild(dir);
      currentInodeDirectory.setLastModificationTimeMs(options.getOperationTimeMs());
      createdInodes.add(dir);
      mInodes.add(dir);
      currentInodeDirectory = (InodeDirectory) dir;
    }

    // Create the final path component. First we need to make sure that there isn't already a file
    // here with that name. If there is an existing file that is a directory and we're creating a
    // directory, nothing needs to be done.
    Inode lastInode = currentInodeDirectory.getChild(name);
    if (lastInode != null) {
      if (lastInode.isDirectory() && options.isDirectory()) {
        return new CreatePathResult();
      }
      LOG.info("FileAlreadyExistsException: " + path);
      throw new FileAlreadyExistsException(path.toString());
    }
    if (options.isDirectory()) {
      lastInode = new InodeDirectory(name, mDirectoryIdGenerator.getNewDirectoryId(),
          currentInodeDirectory.getId(), options.getOperationTimeMs());
    } else {
      lastInode = new InodeFile(name, mContainerIdGenerator.getNewContainerId(),
              currentInodeDirectory.getId(), options.getBlockSizeBytes(),
              options.getOperationTimeMs(), options.getTTL());
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

    LOG.debug("createFile: File Created: {} parent: ", lastInode, currentInodeDirectory);
    return new CreatePathResult(modifiedInodes, createdInodes);
  }