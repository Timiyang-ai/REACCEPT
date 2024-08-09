@CheckReturnValue public final JsonAdapter<T> nonNull() {
    final JsonAdapter<T> delegate = this;
    return new JsonAdapter<T>() {
      @Override public @Nullable T fromJson(JsonReader reader) throws IOException {
        if (reader.peek() == JsonReader.Token.NULL) {
          throw new JsonDataException("Unexpected null at " + reader.getPath());
        } else {
          return delegate.fromJson(reader);
        }
      }
      @Override public void toJson(JsonWriter writer, @Nullable T value) throws IOException {
        if (value == null) {
          throw new JsonDataException("Unexpected null at " + writer.getPath());
        } else {
          delegate.toJson(writer, value);
        }
      }
      @Override boolean isLenient() {
        return delegate.isLenient();
      }
      @Override public String toString() {
        return delegate + ".nonNull()";
      }
    };
  }