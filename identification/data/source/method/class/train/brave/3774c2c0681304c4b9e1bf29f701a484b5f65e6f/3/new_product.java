public ScopedSpan startScopedSpanWithParent(String name, @Nullable TraceContext parent) {
    if (name == null) throw new NullPointerException("name == null");
    if (parent == null) parent = currentTraceContext.get();
    TraceContext context =
      parent != null ? decorateContext(parent, parent.spanId(), 0L) : newRootContext(0);
    return newScopedSpan(name, context);
  }