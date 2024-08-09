public static MediaType valueOf(final String type) {
    return parse(type).get(0);
  }