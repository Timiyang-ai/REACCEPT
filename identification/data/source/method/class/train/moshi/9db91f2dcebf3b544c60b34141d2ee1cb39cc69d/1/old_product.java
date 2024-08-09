@CheckReturnValue public final JsonAdapter<T> nonNull() {
    return new NonNullJsonAdapter<>(this);
  }