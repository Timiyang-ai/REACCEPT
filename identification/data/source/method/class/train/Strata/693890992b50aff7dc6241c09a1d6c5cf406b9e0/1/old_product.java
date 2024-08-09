public static <T> BinaryOperator<T> ensureOnlyOne() {
    return (a, b) -> {
      throw new IllegalArgumentException(Messages.format("Duplicates found: {} and {}", a, b));
    };
  }