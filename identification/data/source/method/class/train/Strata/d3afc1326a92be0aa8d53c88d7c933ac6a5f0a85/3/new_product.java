public static double parseDoublePercent(String str) {
    try {
      return new BigDecimal(str).movePointLeft(2).doubleValue();
    } catch (NumberFormatException ex) {
      NumberFormatException nfex = new NumberFormatException("Unable to parse percentage from '" + str + "'");
      nfex.initCause(ex);
      throw nfex;
    }
  }