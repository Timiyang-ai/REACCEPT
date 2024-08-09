public static String temporaryFileName(long nonce, String path) {
    return path + ".alluxio." + String.format("0x%016X", nonce) + ".tmp";
  }