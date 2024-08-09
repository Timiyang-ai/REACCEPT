public static void validatePath(String path) throws InvalidPathException {
    boolean invalid = (path == null || path.isEmpty() || path.contains(" "));
    if (!OSUtils.isWindows()) {
      invalid = (invalid || !path.startsWith(AlluxioURI.SEPARATOR));
    }

    if (invalid) {
      throw new InvalidPathException(ExceptionMessage.PATH_INVALID.getMessage(path));
    }
  }