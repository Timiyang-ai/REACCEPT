public Span newTrace() {
    return toSpan(nextContext(null, null, SamplingFlags.EMPTY));
  }