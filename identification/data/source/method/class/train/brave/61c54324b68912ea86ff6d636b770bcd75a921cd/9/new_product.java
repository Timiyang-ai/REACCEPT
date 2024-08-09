public Span nextSpan() {
    return toSpan(newContextBuilder(currentTraceContext.get(), sampler).build());
  }