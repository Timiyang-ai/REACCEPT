@Deprecated public Span newTrace(SamplingFlags samplingFlags) {
    return toSpan(newRootContext(samplingFlags, Collections.emptyList()));
  }