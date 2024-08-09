public default ObjDoublePredicate<T> negate() {
    return (obj, value) -> !test(obj, value);
  }