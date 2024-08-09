public Span newTrace() {
    return toSpan(newContextBuilder(null, sampler).build());
  }