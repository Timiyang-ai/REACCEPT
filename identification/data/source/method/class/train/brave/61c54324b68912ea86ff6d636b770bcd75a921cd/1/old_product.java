public Span nextSpan(TraceContextOrSamplingFlags extracted) {
    return toSpan(nextTraceContext(extracted));
  }