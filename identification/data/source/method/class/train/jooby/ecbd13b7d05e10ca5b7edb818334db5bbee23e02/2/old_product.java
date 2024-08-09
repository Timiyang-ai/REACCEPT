public static List<MediaType> parse(final @Nonnull String value) {
    return valueOf(value.split(","));
  }