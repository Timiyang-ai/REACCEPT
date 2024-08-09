@CheckReturnValue public final JsonAdapter<T> lenient() {
    final JsonAdapter<T> delegate = this;
    return new JsonAdapter<T>() {
      @Override public @Nullable T fromJson(JsonReader reader) throws IOException {
        boolean lenient = reader.isLenient();
        reader.setLenient(true);
        try {
          return delegate.fromJson(reader);
        } finally {
          reader.setLenient(lenient);
        }
      }
      @Override public void toJson(JsonWriter writer, @Nullable T value) throws IOException {
        boolean lenient = writer.isLenient();
        writer.setLenient(true);
        try {
          delegate.toJson(writer, value);
        } finally {
          writer.setLenient(lenient);
        }
      }
      @Override boolean isLenient() {
        return true;
      }
      @Override public String toString() {
        return delegate + ".lenient()";
      }
    };
  }