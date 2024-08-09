@CheckReturnValue public final JsonAdapter<T> nonNull() {
    if (this instanceof NonNullJsonAdapter) {
      return this;
    }
    return new NonNullJsonAdapter<>(this);
  }