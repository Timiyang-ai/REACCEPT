default <T extends Enum<T>> T toEnum(Throwing.Function<String, T> fn) {
    return toEnum(fn, String::toUpperCase);
  }