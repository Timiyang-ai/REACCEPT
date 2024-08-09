public static void validatePath(String path) throws InvalidPathException {
    if (path == null || path.isEmpty() || !path.startsWith(TachyonURI.SEPARATOR)
        || path.contains(" ")) {
      throw new InvalidPathException("Path " + path + " is invalid.");
    }
  }