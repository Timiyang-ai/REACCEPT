public Span toSpan(TraceContext context) {
    if (context == null) throw new NullPointerException("context == null");
    TraceContext decorated = propagationFactory.decorate(context);
    if (isNoop(context)) return new NoopSpan(decorated);
    return new RealSpan(decorated, recorder, errorParser);
  }