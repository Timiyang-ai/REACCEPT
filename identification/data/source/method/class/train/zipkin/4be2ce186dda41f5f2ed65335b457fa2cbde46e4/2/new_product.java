public ZipkinRule storeSpans(List<Span> spans) {
    storage.spanConsumer().accept(spans);
    return this;
  }