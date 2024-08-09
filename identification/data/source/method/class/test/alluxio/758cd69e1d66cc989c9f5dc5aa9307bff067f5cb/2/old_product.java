public Inode getInodeByPath(TachyonURI path) throws InvalidPathException {
    TraversalResult traversalResult = traverseToInode(PathUtils.getPathComponents(path.toString()));
    if (!traversalResult.isFound()) {
      throw new InvalidPathException("Could not find path: " + path);
    }
    return traversalResult.getInode();
  }