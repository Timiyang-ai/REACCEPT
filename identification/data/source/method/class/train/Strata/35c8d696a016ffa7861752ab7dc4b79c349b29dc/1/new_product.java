public static ResourceLocator ofClasspath(String resourceName) {
    ArgChecker.notNull(resourceName, "classpathLocator");
    String searchName = resourceName.startsWith("/") ? resourceName : "/" + resourceName;
    Class<?> caller = Guavate.callerClass(3);
    return ofClasspath(caller, searchName);
  }