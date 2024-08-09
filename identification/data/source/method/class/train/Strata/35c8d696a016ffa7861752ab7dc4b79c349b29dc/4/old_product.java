public static ResourceLocator ofClasspath(String resourceName) {
    ArgChecker.notNull(resourceName, "classpathLocator");
    URL url = classLoader().getResource(resourceName);
    if (url == null) {
      throw new IllegalArgumentException("Resource not found: " + resourceName);
    }
    return ofClasspathUrl(url);
  }