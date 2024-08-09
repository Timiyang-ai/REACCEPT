public static final String temporaryFileName(long fileId, long nonce, String path) {
    return path + ".tachyon." + fileId + "." + String.format("0x%16s", nonce) + ".tmp";
  }