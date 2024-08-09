public static ResourceLocator ofPath(Path path) {
    ArgChecker.notNull(path, "path");
    return ofFile(path.toFile());
  }