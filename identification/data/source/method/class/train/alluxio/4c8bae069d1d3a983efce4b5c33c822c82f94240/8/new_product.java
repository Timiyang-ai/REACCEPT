public boolean inodePathExists(AlluxioURI path) {
    try {
      TraversalResult traversalResult =
          traverseToInode(PathUtils.getPathComponents(path.toString()), LockMode.READ);
      traversalResult.getInodeLockGroup().close();
      return traversalResult.isFound();
    } catch (InvalidPathException e) {
      return false;
    }
  }