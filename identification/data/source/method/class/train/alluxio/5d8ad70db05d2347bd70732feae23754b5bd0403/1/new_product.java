public static boolean isTemporaryFileName(String path) {
    return path.matches(TEMPORARY_SUFFIX);
  }