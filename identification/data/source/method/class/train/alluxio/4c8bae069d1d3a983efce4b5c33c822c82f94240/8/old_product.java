public boolean inodePathExists(AlluxioURI path) {
    try {
      TraversalResult traversalResult =
          traverseToInode(PathUtils.getPathComponents(path.toString()), LockMode.READ);
      return traversalResult.isFound();
    } catch (InvalidPathException e) {
      return false;
    }
  }