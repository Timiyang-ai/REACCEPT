public Span nextSpan() {
    TraceContext parent = currentTraceContext.get();
    return parent == null ? newTrace() : newChild(parent);
  }