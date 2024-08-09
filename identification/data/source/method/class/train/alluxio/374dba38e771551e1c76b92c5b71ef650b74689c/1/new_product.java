public static String stripLeadingAndTrailingQuotes(String str) {
    int length = str.length();
    if (length > 1 && str.startsWith("\"") && str.endsWith("\"")) {
      str = str.substring(1, length - 1);
    }
    return str;
  }