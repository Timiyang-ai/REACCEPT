public static <T> BinaryOperator<T> ensureOnlyOne() {
    return (a, b) -> {
      throw new IllegalArgumentException(Messages.format(
          "Multiple values found where only one was expected: {} and {}", a, b));
    };
  }