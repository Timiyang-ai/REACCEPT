public Span newTrace() {
    return ensureSampled(nextContext(null, SamplingFlags.EMPTY));
  }