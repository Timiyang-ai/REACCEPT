public static String rightTrim(String str) {
    if (str == null) {
      return null;
    }
    if (str.length() == 0) {
      return EMPTY_STRING;
    }
    int j = str.length();
    for (int i = str.length() - 1; i >= 0; --i) {
      if (Character.isWhitespace(str.charAt(i))) {
        j--;
      } else {
        break;
      }
    }
    return str.substring(0, j);
  }