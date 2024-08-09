default ObjIntPredicate<T> and(ObjIntPredicate<? super T> other) {
      Objects.requireNonNull(other);
      return (T t, int u) -> test(t, u) && other.test(t, u);
  }