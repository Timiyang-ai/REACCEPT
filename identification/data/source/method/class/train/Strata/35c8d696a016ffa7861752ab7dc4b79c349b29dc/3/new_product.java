public static ResourceLocator ofClasspath(Class<?> cls, String resourceName) {
    ArgChecker.notNull(resourceName, "classpathLocator");
    URL url = cls.getResource(resourceName);
    if (url == null) {
      throw new IllegalArgumentException("Resource not found: " + resourceName);
    }
    return ofClasspathUrl(url);
  }