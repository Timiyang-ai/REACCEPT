public boolean inodePathExists(AlluxioURI uri) {
    try {
      TraversalResult traversalResult =
          traverseToInode(PathUtils.getPathComponents(uri.getPath()), false);
      return traversalResult.isFound();
    } catch (InvalidPathException e) {
      return false;
    }
  }