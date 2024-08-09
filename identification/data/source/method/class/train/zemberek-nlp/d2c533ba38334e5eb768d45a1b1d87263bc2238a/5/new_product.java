public static String repeat(char c, int count) {
    if (count < 1) {
      return EMPTY_STRING;
    }
    char[] chars = new char[count];
    Arrays.fill(chars, c);
    return new String(chars);
  }