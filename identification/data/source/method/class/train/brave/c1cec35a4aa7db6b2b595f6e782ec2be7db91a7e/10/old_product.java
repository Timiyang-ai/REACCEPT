public Span newChild(TraceContext parent) {
    if (parent == null) throw new NullPointerException("parent == null");
    return nextSpan(TraceContextOrSamplingFlags.create(parent));
  }