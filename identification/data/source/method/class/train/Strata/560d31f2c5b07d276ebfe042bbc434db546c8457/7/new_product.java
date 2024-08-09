public default ObjLongPredicate<T> negate() {
    return (obj, value) -> !test(obj, value);
  }