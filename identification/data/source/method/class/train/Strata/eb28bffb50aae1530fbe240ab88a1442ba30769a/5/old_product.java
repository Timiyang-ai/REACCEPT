public static <T> BinaryOperator<T> binaryOperator(CheckedBinaryOperator<T> function) {
    return (t, u) -> {
      try {
        return function.apply(t, u);
      } catch (IOException ex) {
        throw new UncheckedIOException(ex);
      } catch (Throwable ex) {
        throw Throwables.propagate(ex);
      }
    };
  }