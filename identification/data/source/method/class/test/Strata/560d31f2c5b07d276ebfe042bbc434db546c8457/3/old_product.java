default ObjDoublePredicate<T> negate() {
      return (T t, double u) -> !test(t, u);
  }