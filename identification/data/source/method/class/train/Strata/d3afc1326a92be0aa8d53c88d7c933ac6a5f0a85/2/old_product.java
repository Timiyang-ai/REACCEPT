public static double parseDouble(String str) {
    return new BigDecimal(str).doubleValue();
  }