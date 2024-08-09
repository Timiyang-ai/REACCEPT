public static void validatePath(String path) throws InvalidPathException {
    boolean invalid = (path == null || path.isEmpty() || path.contains(" "));
    if (!OSUtils.isWindows()) {
      invalid = (invalid || !path.startsWith(TachyonURI.SEPARATOR));
    }

    if (invalid) {
      throw new InvalidPathException("Path " + path + " is invalid.");
    }
  }