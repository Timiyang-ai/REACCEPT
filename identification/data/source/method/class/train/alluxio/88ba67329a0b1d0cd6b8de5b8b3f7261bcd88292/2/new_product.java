public static String getPermanentFileName(String path) {
    if (isTemporaryFileName(path)) {
      return path.substring(0, path.length() - TEMPORARY_SUFFIX_LENGTH);
    }
    return path;
  }