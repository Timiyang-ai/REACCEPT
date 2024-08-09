public static List<MediaType> parse(final String value) {
    return cache.computeIfAbsent(value, MediaType::parseInternal);
  }