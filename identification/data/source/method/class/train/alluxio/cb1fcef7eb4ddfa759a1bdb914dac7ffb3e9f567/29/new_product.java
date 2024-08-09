public static String temporaryFileName(long nonce, String path) {
    return path + ".tachyon." + String.format("0x%16X", nonce) + ".tmp";
  }