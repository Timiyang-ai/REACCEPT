public static String temporaryFileName(long nonce, String path) {
    return path + String.format(TEMPORARY_SUFFIX_FORMAT, nonce);
  }