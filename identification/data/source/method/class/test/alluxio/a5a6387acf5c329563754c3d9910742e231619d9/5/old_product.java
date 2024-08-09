public Inode createPath(TachyonURI path, long blockSizeBytes, boolean recursive,
      boolean directory)
          throws FileAlreadyExistException, BlockInfoException, InvalidPathException {

    if (path.isRoot()) {
      LOG.info("FileAlreadyExistException: " + path);
      throw new FileAlreadyExistException(path.toString());
    }

    if (!directory && blockSizeBytes < 1) {
      throw new BlockInfoException("Invalid block size " + blockSizeBytes);
    }

    LOG.debug("createPath {}", FormatUtils.parametersToString(path));

    String[] pathComponents = PathUtils.getPathComponents(path.toString());
    String name = path.getName();

    String[] parentPath = new String[pathComponents.length - 1];
    System.arraycopy(pathComponents, 0, parentPath, 0, parentPath.length);

    long creationTimeMs = System.currentTimeMillis();

    TraversalResult traversalResult = traverseToInode(parentPath);
    // pathIndex is the index into pathComponents where we start filling in the path from the inode.
    int pathIndex = parentPath.length;
    if (!traversalResult.isFound()) {
      // Then the path component at errorInd k doesn't exist. If it's not recursive, we throw an
      // exception here. Otherwise we add the remaining path components to the list of components
      // to create.
      if (!recursive) {
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
    // Fill in the directories that were missing.
    for (int k = pathIndex; k < parentPath.length; k ++) {
      Inode dir = new InodeDirectory(pathComponents[k], mDirectoryIdGenerator.getNewDirectoryId(),
          currentInodeDirectory.getId(), creationTimeMs);
      dir.setPinned(currentInodeDirectory.isPinned());
      currentInodeDirectory.addChild(dir);
      currentInodeDirectory.setLastModificationTimeMs(creationTimeMs);
      mInodes.add(dir);
      currentInodeDirectory = (InodeDirectory) dir;
    }

    // Create the final path component. First we need to make sure that there isn't already a file
    // here with that name. If there is an existing file that is a directory and we're creating a
    // directory, we just return the existing directory's id.
    Inode ret = currentInodeDirectory.getChild(name);
    if (ret != null) {
      if (ret.isDirectory() && directory) {
        return ret;
      }
      LOG.info("FileAlreadyExistException: " + path);
      throw new FileAlreadyExistException(path.toString());
    }
    if (directory) {
      ret = new InodeDirectory(name, mDirectoryIdGenerator.getNewDirectoryId(),
          currentInodeDirectory.getId(), creationTimeMs);
    } else {
      ret = new InodeFile(name, mContainerIdGenerator.getNewContainerId(),
          currentInodeDirectory.getId(), blockSizeBytes, creationTimeMs);
      if (currentInodeDirectory.isPinned()) {
        // Update set of pinned file ids.
        mPinnedInodeFileIds.add(ret.getId());
      }
    }
    ret.setPinned(currentInodeDirectory.isPinned());

    mInodes.add(ret);
    currentInodeDirectory.addChild(ret);
    currentInodeDirectory.setLastModificationTimeMs(creationTimeMs);

    LOG.debug("createFile: File Created: {} parent: ", ret, currentInodeDirectory);
    return ret;
  }