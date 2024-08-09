public static long toLong(String num, long defaultValue) {
    if (num != null) {
      try {
        return Long.parseLong(num);
      } catch (NumberFormatException e) {
        logger.trace("", e);
      }
    }
    return defaultValue;
  }