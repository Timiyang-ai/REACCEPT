public ZipkinRule storeSpans(List<Span> spans) {
    store.accept(spans);
    return this;
  }