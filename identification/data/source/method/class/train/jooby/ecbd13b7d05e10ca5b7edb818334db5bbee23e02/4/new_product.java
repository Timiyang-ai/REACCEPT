public static List<MediaType> parse(final String value) {
    try {
      return cache.getUnchecked(value);
    } catch (UncheckedExecutionException ex) {
      throw Throwables.propagate(ex.getCause());
    }
  }