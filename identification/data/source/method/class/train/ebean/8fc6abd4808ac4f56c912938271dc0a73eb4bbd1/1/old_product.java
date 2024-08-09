public static String hash(String content) {

    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] digest = md.digest(content.getBytes("UTF-8"));
      return digestToHex(digest);
    } catch (Exception e) {
      throw new RuntimeException("MD5 hashing failed", e);
    }
  }