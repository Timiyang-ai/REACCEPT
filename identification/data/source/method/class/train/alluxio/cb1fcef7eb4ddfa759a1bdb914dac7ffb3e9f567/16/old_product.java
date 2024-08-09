public static String temporaryFileName(long nonce, String path) {
    try {
      return path + ".alluxio." + URLEncoder.encode(String.format("0x%16X", nonce),
          "UTF-8") + ".tmp";
    } catch (UnsupportedEncodingException e) {
      return path + ".alluxio." + String.format("0x%16X", nonce) + ".tmp";
    }
  }