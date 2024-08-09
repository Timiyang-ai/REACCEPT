public static int toIntHex(String num, int defaultValue) {
    try {
      if (num != null && num.startsWith("#")) {
        num = num.substring(1);
      }
      return Integer.parseInt(num, 16);
    } catch (NumberFormatException e) {
      logger.trace("", e);
      return defaultValue;
    }
  }