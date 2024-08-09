public static String insertFromLeft(String str, int interval, String stringToInsert) {
    if (interval < 0) {
      throw new IllegalArgumentException("interval value cannot be negative.");
    }
    if (str == null || interval == 0 || interval >= str.length() || isNullOrEmpty(stringToInsert)) {
      return str;
    }
    StringBuilder b = new StringBuilder();
    int i = 0;
    for (char c : str.toCharArray()) {
      b.append(c);
      i++;
      if (i % interval == 0 && i <= str.length() - 1) {
        b.append(stringToInsert);
      }
    }
    return b.toString();
  }