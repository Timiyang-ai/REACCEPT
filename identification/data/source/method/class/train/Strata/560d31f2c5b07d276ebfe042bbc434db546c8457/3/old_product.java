default ObjLongPredicate<T> and(ObjLongPredicate<? super T> other) {
      Objects.requireNonNull(other);
      return (T t, long u) -> test(t, u) && other.test(t, u);
  }