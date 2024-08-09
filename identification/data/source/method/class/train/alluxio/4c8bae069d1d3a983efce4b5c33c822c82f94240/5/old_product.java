public boolean inodePathExists(AlluxioURI path) {
    try {
      TraversalResult traversalResult =
          traverseToInode(PathUtils.getPathComponents(path.toString()), false);
      return traversalResult.isFound();
    } catch (InvalidPathException e) {
      return false;
    }
  }