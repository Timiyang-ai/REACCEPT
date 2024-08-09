public default ObjIntPredicate<T> negate() {
    return (obj, value) -> !test(obj, value);
  }