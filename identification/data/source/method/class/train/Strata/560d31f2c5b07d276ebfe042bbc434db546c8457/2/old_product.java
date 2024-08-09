default ObjDoublePredicate<T> and(ObjDoublePredicate<? super T> other) {
      Objects.requireNonNull(other);
      return (T t, double u) -> test(t, u) && other.test(t, u);
  }