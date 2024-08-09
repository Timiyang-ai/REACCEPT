public boolean inodePathExists(AlluxioURI path) {
    try {
      TraversalResult traversalResult =
          traverseToInode(PathUtils.getPathComponents(path.getPath()), false);
      return traversalResult.isFound();
    } catch (InvalidPathException e) {
      return false;
    }
  }