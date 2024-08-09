public static <T> BinaryOperator<T> binaryOperator(CheckedBinaryOperator<T> function) {
    return (t, u) -> {
      try {
        return function.apply(t, u);
      } catch (Throwable ex) {
        throw propagate(ex);
      }
    };
  }