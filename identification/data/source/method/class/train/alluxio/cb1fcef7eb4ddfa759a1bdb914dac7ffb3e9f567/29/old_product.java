public static String temporaryFileName(long fileId, long nonce, String path) {
    return path + ".tachyon." + fileId + "." + String.format("0x%16X", nonce) + ".tmp";
  }