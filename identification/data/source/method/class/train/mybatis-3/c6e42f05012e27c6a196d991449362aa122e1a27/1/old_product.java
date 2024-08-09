public ResolverUtil<T> find(Test test, String packageName) {
    String path = getPackagePath(packageName);

    try {
      List<URL> urls = Collections.list(getClassLoader().getResources(path));
      for (URL url : urls) {
        List<String> children = listClassResources(url, path);
        for (String child : children) {
          addIfMatching(test, child);
        }
      }
    }
    catch (IOException ioe) {

      log.error("Could not read package: " + packageName + " -- ", ioe);
    }

    return this;
  }