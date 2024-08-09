public static ResourceLocator ofClasspath(String resourceName) {
    ArgChecker.notNull(resourceName, "classpathLocator");
    String searchName = resourceName.startsWith("/") ? resourceName : "/" + resourceName;
    URL url = ResourceLocator.class.getResource(searchName);
    if (url == null) {
      throw new IllegalArgumentException("Resource not found: " + searchName);
    }
    return ofClasspathUrl(url);
  }