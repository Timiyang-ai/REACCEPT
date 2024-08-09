default ObjIntPredicate<T> and(ObjIntPredicate<? super T> other) {
    Objects.requireNonNull(other);
    return (obj, value) -> test(obj, value) && other.test(obj, value);
  }