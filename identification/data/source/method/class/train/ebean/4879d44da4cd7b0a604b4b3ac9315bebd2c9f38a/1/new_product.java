public static String trim(String sql) {
    return LINUX_NEW_LINE_REPLACE_PATTERN.matcher(sql).replaceAll(Matcher.quoteReplacement("\\n "));
  }