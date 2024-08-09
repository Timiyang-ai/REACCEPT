public final JsonAdapter<T> serializeNulls() {
    final JsonAdapter<T> delegate = this;
    return new JsonAdapter<T>() {
      @Override public T fromJson(JsonReader reader) throws IOException {
        return delegate.fromJson(reader);
      }
      @Override public void toJson(JsonWriter writer, T value) throws IOException {
        boolean serializeNulls = writer.getSerializeNulls();
        writer.setSerializeNulls(true);
        try {
          delegate.toJson(writer, value);
        } finally {
          writer.setSerializeNulls(serializeNulls);
        }
      }
      @Override public String toString() {
        return delegate + ".serializeNulls()";
      }
    };
  }