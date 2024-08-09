public static int parseInteger(String str) {
    try {
      return Integer.parseInt(str);
    } catch (NumberFormatException ex) {
      NumberFormatException nfex = new NumberFormatException("Unable to parse integer from '" + str + "'");
      nfex.initCause(ex);
      throw nfex;
    }
  }