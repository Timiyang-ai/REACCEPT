public static int toInt(String num, int defaultValue) {
    if (num != null) {
      try {
        return Integer.parseInt(num);
      } catch (NumberFormatException e) {
        logger.trace("", e);
      }
    }
    return defaultValue;
  }