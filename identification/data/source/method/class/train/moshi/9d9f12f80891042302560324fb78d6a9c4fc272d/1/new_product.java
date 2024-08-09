@CheckReturnValue public final JsonAdapter<T> failOnUnknown() {
    final JsonAdapter<T> delegate = this;
    return new JsonAdapter<T>() {
      @Override public @Nullable T fromJson(JsonReader reader) throws IOException {
        boolean skipForbidden = reader.failOnUnknown();
        reader.setFailOnUnknown(true);
        try {
          return delegate.fromJson(reader);
        } finally {
          reader.setFailOnUnknown(skipForbidden);
        }
      }
      @Override public void toJson(JsonWriter writer, @Nullable T value) throws IOException {
        delegate.toJson(writer, value);
      }
      @Override boolean isLenient() {
        return delegate.isLenient();
      }
      @Override public String toString() {
        return delegate + ".failOnUnknown()";
      }
    };
  }