public static float toFloat(String num, float defaultValue) {
    if (num != null) {
      try {
        return Float.parseFloat(num);
      } catch (NumberFormatException e) {
        logger.trace("", e);
      }
    }
    return defaultValue;
  }