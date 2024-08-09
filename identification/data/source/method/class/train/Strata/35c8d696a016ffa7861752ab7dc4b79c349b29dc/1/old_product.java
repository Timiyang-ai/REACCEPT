public static ResourceLocator ofClasspath(String resourceName) {
    ArgChecker.notNull(resourceName, "classpathLocator");
    String searchName = resourceName.startsWith("/") ? resourceName : "/" + resourceName;
    if (STACK_WALKER_METHOD != null) {
      try {
        return ofClasspath((Class<?>) STACK_WALKER_METHOD.invokeExact(), searchName);
      } catch (Throwable ex) {
        throw new RuntimeException(ex);
      }
    }
    return ofClasspath(ResourceLocator.class, searchName);
  }