public static String reverse(String str) {
    if (str == null) {
      return null;
    }
    if (str.length() == 0) {
      return EMPTY_STRING;
    }
    return new StringBuilder(str).reverse().toString();
  }