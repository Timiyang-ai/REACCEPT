public static List<MediaType> parse(final String value) throws Err.BadMediaType {
    return cache.computeIfAbsent(value, MediaType::parseInternal);
  }