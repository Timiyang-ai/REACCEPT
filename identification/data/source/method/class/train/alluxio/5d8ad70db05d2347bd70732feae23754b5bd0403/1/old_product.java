public static boolean isTemporaryFileName(String path) {
    return path.matches("^.*\\.alluxio\\.0x[0-9A-F]{16}\\.tmp$");
  }