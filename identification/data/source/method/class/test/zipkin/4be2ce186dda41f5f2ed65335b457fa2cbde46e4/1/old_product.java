public ZipkinRule storeSpans(Iterable<Span> spans) {
    store.accept(spans.iterator());
    return this;
  }