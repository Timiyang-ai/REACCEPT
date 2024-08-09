public static String escapeString(final String str) {
    // Allocate a buffer big enough to cover the case with a string needed
    // very excessive escaping without having to reallocate the buffer.
    final StringBuilder result = new StringBuilder(3 * str.length());

    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      if (c < NON_PRINTABLE_CHARS.length) {
        result.append(NON_PRINTABLE_CHARS[c]);
      } else if (c == '\\') {
        result.append("\\\\");
      } else {
        result.append(c);
      }
    }
    return result.toString();
  }