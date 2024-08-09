public static double parseDoublePercent(String str) {
    return new BigDecimal(str).movePointLeft(2).doubleValue();
  }