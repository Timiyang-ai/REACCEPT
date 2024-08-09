@Deprecated public Span newTrace(SamplingFlags samplingFlags) {
    return toSpan(newContextBuilder(null, samplingFlags).build());
  }