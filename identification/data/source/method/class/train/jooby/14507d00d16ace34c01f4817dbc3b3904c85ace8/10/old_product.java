public static List<MediaType> valueOf(final @Nonnull String... types) {
    requireNonNull(types, "Types are required.");
    List<MediaType> result = new ArrayList<>();
    for (String type : types) {
      result.add(valueOf(type));
    }
    return result;
  }