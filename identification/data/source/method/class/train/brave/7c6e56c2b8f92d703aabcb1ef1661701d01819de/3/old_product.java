public Span newTrace() {
    return toSpan(nextContext(null, SamplingFlags.EMPTY));
  }