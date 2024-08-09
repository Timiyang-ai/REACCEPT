default ObjIntPredicate<T> negate() {
      return (T t, int u) -> !test(t, u);
  }