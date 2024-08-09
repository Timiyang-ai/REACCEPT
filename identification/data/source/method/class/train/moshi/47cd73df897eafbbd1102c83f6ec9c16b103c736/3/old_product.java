public final JsonAdapter<T> nullSafe() {
    final JsonAdapter<T> delegate = this;
    return new JsonAdapter<T>() {
      @Override public @Nullable T fromJson(JsonReader reader) throws IOException {
        if (reader.peek() == JsonReader.Token.NULL) {
          return reader.nextNull();
        } else {
          return delegate.fromJson(reader);
        }
      }
      @Override public void toJson(JsonWriter writer, @Nullable T value) throws IOException {
        if (value == null) {
          writer.nullValue();
        } else {
          delegate.toJson(writer, value);
        }
      }
      @Override public String toString() {
        return delegate + ".nullSafe()";
      }
    };
  }