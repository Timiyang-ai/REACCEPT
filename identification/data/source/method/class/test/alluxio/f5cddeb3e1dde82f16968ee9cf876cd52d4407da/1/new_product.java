public static String encodeJson(String input) {
    return "\"" + StringEscapeUtils.escapeJava(input) + "\"";
  }