public static @Nonnull String databaseType(@Nonnull String url) {
    String type = Arrays.stream(url.toLowerCase().split(":"))
        .filter(token -> !SKIP_TOKENS.contains(token))
        .findFirst()
        .orElse(url);
    return type;
  }