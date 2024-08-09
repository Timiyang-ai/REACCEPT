public static ResourceLocator ofUrl(URL url) {
    ArgChecker.notNull(url, "url");
    String filename = url.toString();
    return new ResourceLocator(URL_PREFIX + filename, Resources.asByteSource(url));
  }