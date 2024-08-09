public boolean inodePathExists(AlluxioURI uri) {
    try {
      TraversalResult traversalResult =
          traverseToInode(PathUtils.getPathComponents(uri.getPath()), LockPattern.READ);
      traversalResult.getInodeLockList().close();
      return traversalResult.isFound();
    } catch (InvalidPathException e) {
      return false;
    }
  }