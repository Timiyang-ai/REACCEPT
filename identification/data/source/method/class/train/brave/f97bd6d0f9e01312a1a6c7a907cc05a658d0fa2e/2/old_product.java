public ScopedSpan startScopedSpan(String name) {
    return startScopedSpanWithParent(name, currentTraceContext.get());
  }