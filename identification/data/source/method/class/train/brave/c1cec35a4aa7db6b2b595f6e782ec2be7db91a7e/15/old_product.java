public Span newChild(TraceContext parent) {
    if (parent == null) throw new NullPointerException("parent == null");
    if (Boolean.FALSE.equals(parent.sampled())) {
      return new NoopSpan(parent);
    }
    return ensureSampled(nextContext(parent, parent));
  }