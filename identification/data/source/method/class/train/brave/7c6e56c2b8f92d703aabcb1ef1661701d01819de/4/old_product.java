public Span newTrace(SamplingFlags samplingFlags) {
    return ensureSampled(nextContext(null, samplingFlags));
  }