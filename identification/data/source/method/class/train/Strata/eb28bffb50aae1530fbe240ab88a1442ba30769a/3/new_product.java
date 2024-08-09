public static <T> UnaryOperator<T> unaryOperator(CheckedUnaryOperator<T> function) {
    return (t) -> {
      try {
        return function.apply(t);
      } catch (Throwable ex) {
        throw propagate(ex);
      }
    };
  }