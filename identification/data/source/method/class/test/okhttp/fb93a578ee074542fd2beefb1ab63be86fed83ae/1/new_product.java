public static @Nullable MediaType parse(String string) {
    try {
      return get(string);
    } catch (IllegalArgumentException ignored) {
      return null;
    }
  }