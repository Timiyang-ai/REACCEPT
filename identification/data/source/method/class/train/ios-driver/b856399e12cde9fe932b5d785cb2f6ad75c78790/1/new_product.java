public static String getRegexPattern(String original) {
    String res = original;
    res = specialCharsPattern.matcher(res).replaceAll("\\\\$1");
    res = specifierPattern.matcher(res).replaceAll("(.*){1}");
    return res;
  }