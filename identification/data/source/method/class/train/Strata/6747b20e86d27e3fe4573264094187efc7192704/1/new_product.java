public static ResourceLocator ofPath(Path path) {
    ArgChecker.notNull(path, "path");
    try {
      return ofFile(path.toFile());
    } catch (UnsupportedOperationException ex) {
      try {
        return ofUrl(path.toUri().toURL());
      } catch (MalformedURLException ex2) {
        throw new IllegalArgumentException("Path could not be converted to a File or URL: " + path);
      }
    }
  }