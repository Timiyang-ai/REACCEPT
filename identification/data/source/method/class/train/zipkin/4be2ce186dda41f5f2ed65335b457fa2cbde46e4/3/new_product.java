public ZipkinRule storeSpans(List<Span> spans) {
    try {
      storage.accept(V2SpanConverter.fromSpans(spans)).execute();
    } catch (IOException e) {
      throw Platform.get().uncheckedIOException(e);
    }
    return this;
  }