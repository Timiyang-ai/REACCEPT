public static String getPermanentFileName(String path) {
    if (isTemporaryFileName(path)) {
      // Guaranteed to only strip the suffix, since it includes the end of the string.
      return path.replace(TEMPORARY_SUFFIX, "");
    }
    return path;
  }