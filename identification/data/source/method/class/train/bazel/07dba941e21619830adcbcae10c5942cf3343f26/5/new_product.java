public static String replaceAllLiteral(String input, String literal,
                                         String replacement) {
    int literalLength = literal.length();
    if (literalLength == 0) {
      return input;
    }
    StringBuilder result = new StringBuilder(
        input.length() + replacement.length());
    int start = 0;
    int index = 0;

    while ((index = input.indexOf(literal, start)) >= 0) {
      result.append(input, start, index);
      result.append(replacement);
      start = index + literalLength;
    }
    result.append(input.substring(start));
    return result.toString();
  }