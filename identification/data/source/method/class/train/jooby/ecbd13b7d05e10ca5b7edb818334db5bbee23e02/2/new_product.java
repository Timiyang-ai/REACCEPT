public static List<MediaType> parse(final String value) {
    return valueOf(value.split(","));
  }