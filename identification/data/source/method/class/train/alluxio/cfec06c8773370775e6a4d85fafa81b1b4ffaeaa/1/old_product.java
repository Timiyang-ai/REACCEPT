public boolean isFullyInMemory(TachyonURI path)
      throws FileDoesNotExistException, InvalidPathException {
    return getInMemoryPercentage(path) == 100;
  }