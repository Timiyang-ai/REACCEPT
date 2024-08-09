public static String getRegexPattern(String original) {
    String res = original.replace("%@", "(.*){1}");
    res = res.replaceAll("%1\\$@", "(.*){1}");
    res = res.replaceAll("%2\\$@", "(.*){1}");
    res = res.replaceAll("%3\\$@", "(.*){1}");
    res = res.replaceAll("%d", "(.*){1}");
    return res;
  }