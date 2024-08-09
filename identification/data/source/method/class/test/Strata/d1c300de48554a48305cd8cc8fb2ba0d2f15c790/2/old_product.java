public static ResourceLocator ofClasspathUrl(URL url) {
    ArgChecker.notNull(url, "url");
    String locator = CLASSPATH_URL_PREFIX + url.toString();
    return new ResourceLocator(locator, Resources.asByteSource(url));
  }