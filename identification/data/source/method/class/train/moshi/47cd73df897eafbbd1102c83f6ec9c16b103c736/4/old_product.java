@CheckReturnValue public final JsonAdapter<T> nullSafe() {
    return new NullSafeJsonAdapter<>(this);
  }