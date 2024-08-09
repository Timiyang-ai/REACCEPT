public boolean inodePathExists(AlluxioURI path) {
    try {
      TraversalResult traversalResult =
          traverseToInode(PathUtils.getPathComponents(path.toString()), false, LockMode.READ);
      return traversalResult.isFound();
    } catch (InvalidPathException e) {
      return false;
    }
  }