public static <T> Predicate<T> predicate(CheckedPredicate<T> predicate) {
    return (t) -> {
      try {
        return predicate.test(t);
      } catch (Throwable ex) {
        throw propagate(ex);
      }
    };
  }