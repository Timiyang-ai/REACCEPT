public static MediaType valueOf(final String type) throws Err.BadMediaType {
    return parse(type).get(0);
  }