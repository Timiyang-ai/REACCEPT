@Nonnull default <T extends Enum<T>> T toEnum(@Nonnull Sneaky.Function<String, T> fn) {
    return toEnum(fn, String::toUpperCase);
  }