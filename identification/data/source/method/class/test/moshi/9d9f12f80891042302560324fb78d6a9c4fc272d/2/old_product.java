public final JsonAdapter<T> lenient() {
    final JsonAdapter<T> delegate = this;
    return new JsonAdapter<T>() {
      @Override public T fromJson(JsonReader reader) throws IOException {
        boolean lenient = reader.isLenient();
        reader.setLenient(true);
        try {
          return delegate.fromJson(reader);
        } finally {
          reader.setLenient(lenient);
        }
      }
      @Override public void toJson(JsonWriter writer, T value) throws IOException {
        boolean lenient = writer.isLenient();
        writer.setLenient(true);
        try {
          delegate.toJson(writer, value);
        } finally {
          writer.setLenient(lenient);
        }
      }
      @Override public String toString() {
        return delegate + ".lenient()";
      }
    };
  }