public static boolean match(String filePath, String patternPath) {
    return match(new AlluxioURI(filePath), new AlluxioURI(patternPath));
  }