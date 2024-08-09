public Span newTrace(SamplingFlags samplingFlags) {
    return toSpan(nextContext(null, null, samplingFlags));
  }