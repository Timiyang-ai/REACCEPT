public Span newChild(TraceContext parent) {
    if (parent == null) throw new NullPointerException("parent == null");
    return _toSpan(decorateContext(parent, parent.spanId(), 0L));
  }