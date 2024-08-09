public static @Nullable HttpUrl parse(String url) {
    try {
      return get(url);
    } catch (IllegalArgumentException ignored) {
      return null;
    }
  }