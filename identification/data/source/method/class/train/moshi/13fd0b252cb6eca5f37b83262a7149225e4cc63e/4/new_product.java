@CheckReturnValue public JsonAdapter<T> indent(final String indent) {
    if (indent == null) {
      throw new NullPointerException("indent == null");
    }
    final JsonAdapter<T> delegate = this;
    return new JsonAdapter<T>() {
      @Override public @Nullable T fromJson(JsonReader reader) throws IOException {
        return delegate.fromJson(reader);
      }
      @Override public void toJson(JsonWriter writer, @Nullable T value) throws IOException {
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