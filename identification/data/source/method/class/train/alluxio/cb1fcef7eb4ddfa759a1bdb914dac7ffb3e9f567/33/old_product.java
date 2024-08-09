public static final String temporaryFileName(long fileId, long nonce, String path) {
    return path + ".tachyon." + fileId + "." + nonce + ".tmp";
  }