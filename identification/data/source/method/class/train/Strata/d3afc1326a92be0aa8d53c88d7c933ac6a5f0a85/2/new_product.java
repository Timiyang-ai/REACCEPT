public static double parseDouble(String str) {
    try {
      return new BigDecimal(str).doubleValue();
    } catch (NumberFormatException ex) {
      NumberFormatException nfex = new NumberFormatException("Unable to parse double from '" + str + "'");
      nfex.initCause(ex);
      throw nfex;
    }
  }