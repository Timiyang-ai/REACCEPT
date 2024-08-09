public Span nextSpan() {
    TraceContext parent = currentTraceContext.get();
    return parent != null ? newChild(parent) : newTrace();
  }