public static <T> Predicate<T> predicate(CheckedPredicate<T> predicate) {
    return (t) -> {
      try {
        return predicate.test(t);
      } catch (IOException ex) {
        throw new UncheckedIOException(ex);
      } catch (Throwable ex) {
        throw Throwables.propagate(ex);
      }
    };
  }