public Inode getInodeByPath(AlluxioURI path)
      throws InvalidPathException, FileDoesNotExistException {
    TraversalResult traversalResult =
        traverseToInode(PathUtils.getPathComponents(path.toString()), false);
    if (!traversalResult.isFound()) {
      throw new FileDoesNotExistException(ExceptionMessage.PATH_DOES_NOT_EXIST.getMessage(path));
    }
    return traversalResult.getInode();
  }