public Span newTrace(SamplingFlags samplingFlags) {
    return toSpan(nextContext(TraceContextOrSamplingFlags.create(samplingFlags)));
  }