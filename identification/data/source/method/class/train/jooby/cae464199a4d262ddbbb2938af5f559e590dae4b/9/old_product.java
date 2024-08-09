@Nonnull default <T extends Enum<T>> T toEnum(@Nonnull Throwing.Function<String, T> fn) {
    return toEnum(fn, String::toUpperCase);
  }