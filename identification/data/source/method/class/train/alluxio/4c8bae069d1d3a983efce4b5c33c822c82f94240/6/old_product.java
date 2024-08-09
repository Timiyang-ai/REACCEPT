public boolean inodePathExists(AlluxioURI uri) {
    try {
      TraversalResult traversalResult =
          traverseToInode(PathUtils.getPathComponents(uri.getPath()), LockMode.READ, null);
      traversalResult.getInodeLockGroup().close();
      return traversalResult.isFound();
    } catch (InvalidPathException e) {
      return false;
    }
  }