CreatePathResult createPath(AlluxioURI path, CreatePathOptions<?> options)
      throws FileAlreadyExistsException, BlockInfoException, InvalidPathException, IOException,
      FileDoesNotExistException {
    try (InodePath inodePath = lockInodePath(path, LockMode.WRITE)) {
      return createPath(inodePath, options);
    }
  }