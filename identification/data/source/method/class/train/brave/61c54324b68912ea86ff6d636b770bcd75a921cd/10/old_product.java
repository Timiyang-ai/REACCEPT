public Span nextSpan(TraceContextOrSamplingFlags extracted) {
    TraceContext parent = extracted.context();
    if (parent != null && Boolean.FALSE.equals(parent.sampled())) {
      return NoopSpan.create(parent);
    }
    return toSpan(nextContext(extracted));
  }