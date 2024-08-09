public static <T> UnaryOperator<T> unaryOperator(CheckedUnaryOperator<T> function) {
    return (t) -> {
      try {
        return function.apply(t);
      } catch (IOException ex) {
        throw new UncheckedIOException(ex);
      } catch (Throwable ex) {
        throw Throwables.propagate(ex);
      }
    };
  }