public Span newChild(TraceContext parent) {
    if (parent == null) throw new NullPointerException("parent == null");
    return toSpan(newContextBuilder(parent, sampler).build());
  }