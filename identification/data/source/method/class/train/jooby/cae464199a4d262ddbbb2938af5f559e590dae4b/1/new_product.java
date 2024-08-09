@Nonnull default <T extends Enum<T>> T toEnum(@Nonnull SneakyThrows.Function<String, T> fn) {
    return toEnum(fn, String::toUpperCase);
  }