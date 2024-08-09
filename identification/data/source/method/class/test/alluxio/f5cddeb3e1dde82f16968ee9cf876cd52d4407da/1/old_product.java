public static String encodeJson(String input) {
    return "\"" + StringEscapeUtils.escapeJson(input) + "\"";
  }