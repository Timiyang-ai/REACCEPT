public static Tenor parseTenor(String str) {
    try {
      return Tenor.parse(str);

    } catch (RuntimeException ex) {
      throw new IllegalArgumentException("Unknown tenor format: " + str);
    }
  }