public Span newChild(TraceContext parent) {
    if (parent == null) throw new NullPointerException("parent == null");
    if (Boolean.FALSE.equals(parent.sampled())) {
      return NoopSpan.create(parent);
    }
    return toSpan(nextContext(parent, parent));
  }