@CheckReturnValue public final JsonAdapter<T> nullSafe() {
    if (this instanceof NullSafeJsonAdapter) {
      return this;
    }
    return new NullSafeJsonAdapter<>(this);
  }