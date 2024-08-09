public static String hash(String content) {

    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      return digestToHex(md.digest(content.getBytes(StandardCharsets.UTF_8)));
    } catch (Exception e) {
      throw new RuntimeException("MD5 hashing failed", e);
    }
  }