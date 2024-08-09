public static List<MediaType> valueOf(final @Nonnull String... mediaTypes) {
    requireNonNull(mediaTypes, "A mediaType is required.");
    List<MediaType> result = new ArrayList<>();
    for (String mediaType : mediaTypes) {
      result.add(valueOf(mediaType));
    }
    return result;
  }