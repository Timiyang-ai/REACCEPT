public Span newTrace() {
    return nextSpan(TraceContextOrSamplingFlags.create(SamplingFlags.EMPTY));
  }