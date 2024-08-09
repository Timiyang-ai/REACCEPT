public ZipkinRule storeSpans(List<Span> spans) {
    CallbackCaptor<Void> callback = new CallbackCaptor<>();
    storage.asyncSpanConsumer().accept(spans, callback);
    callback.get();
    return this;
  }