private static String getHeader(Map<String, List<String>> args, String header, boolean required) {
    String value = null;
    if (args.containsKey(header)) {
      List<String> values = args.get(header);
      if (values.size() == 1) {
        value = values.get(0);
      } else {
        throw new IllegalArgumentException("Request has too many values for header: " + header);
      }
    } else if (required) {
      throw new IllegalArgumentException("Request does not have required header: " + header);
    }
    return value;
  }