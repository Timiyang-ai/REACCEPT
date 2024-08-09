public ScopedSpan startScopedSpanWithParent(String name, @Nullable TraceContext parent) {
    if (name == null) throw new NullPointerException("name == null");
    TraceContext context = propagationFactory.decorate(newContextBuilder(parent, sampler).build());
    CurrentTraceContext.Scope scope = currentTraceContext.newScope(context);
    if (isNoop(context)) return new NoopScopedSpan(context, scope);
    ScopedSpan result = new RealScopedSpan(context, scope, recorder, errorParser);
    recorder.name(context, name);
    recorder.start(context);
    return result;
  }