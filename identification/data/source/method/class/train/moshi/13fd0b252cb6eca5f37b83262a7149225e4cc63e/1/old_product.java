public JsonAdapter<T> indent(final String indent) {
    final JsonAdapter<T> delegate = this;
    return new JsonAdapter<T>() {
      @Override public T fromJson(JsonReader reader) throws IOException {
        return delegate.fromJson(reader);
      }
      @Override public void toJson(JsonWriter writer, T value) throws IOException {
        String originalIndent = writer.getIndent();
        writer.setIndent(indent);
        try {
          delegate.toJson(writer, value);
        } finally {
          writer.setIndent(originalIndent);
        }
      }
      @Override public String toString() {
        return delegate + ".indent(\"" + indent + "\")";
      }
    };
  }