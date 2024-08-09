public static String leftTrim(String s) {
    if (s == null) {
      return null;
    }
    if (s.length() == 0) {
      return EMPTY_STRING;
    }
    int j = 0;
    for (int i = 0; i < s.length(); i++) {
      if (Character.isWhitespace(s.charAt(i))) {
        j++;
      } else {
        break;
      }
    }
    return s.substring(j);
  }