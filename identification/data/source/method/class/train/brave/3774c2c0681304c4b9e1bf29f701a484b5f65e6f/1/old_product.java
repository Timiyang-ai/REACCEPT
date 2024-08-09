public ScopedSpan startScopedSpanWithParent(String name, @Nullable TraceContext parent) {
    if (name == null) throw new NullPointerException("name == null");
    TraceContext context = propagationFactory.decorate(newContextBuilder(parent, sampler).build());
    CurrentTraceContext.Scope scope = currentTraceContext.newScope(context);
    ScopedSpan result;
    if (!noop.get() && Boolean.TRUE.equals(context.sampled())) {
      result = new RealScopedSpan(context, scope, recorder, errorParser);
      recorder.name(context, name);
      recorder.start(context);
    } else {
      result = new NoopScopedSpan(context, scope);
    }
    return result;
  }