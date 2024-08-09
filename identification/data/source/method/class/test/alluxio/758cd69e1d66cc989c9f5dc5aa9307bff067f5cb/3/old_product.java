public Inode getInodeByPath(AlluxioURI path) throws InvalidPathException {
    TraversalResult traversalResult =
        traverseToInode(PathUtils.getPathComponents(path.toString()), false);
    if (!traversalResult.isFound()) {
      throw new InvalidPathException(ExceptionMessage.PATH_DOES_NOT_EXIST.getMessage(path));
    }
    return traversalResult.getInode();
  }