public List<List<Span>> getTraces() {
    return storage.spanStore().getRawTraces();
  }