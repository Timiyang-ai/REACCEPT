public Span newTrace() {
    return toSpan(newRootContext(SamplingFlags.EMPTY, Collections.emptyList()));
  }