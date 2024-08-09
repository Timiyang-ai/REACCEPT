default ObjLongPredicate<T> negate() {
      return (T t, long u) -> !test(t, u);
  }