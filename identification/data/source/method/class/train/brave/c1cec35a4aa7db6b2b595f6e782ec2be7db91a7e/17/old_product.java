public Span toSpan(TraceContext context) {
    if (context == null) throw new NullPointerException("context == null");
    TraceContext decorated = propagationFactory.decorate(context);
    if (!noop.get() && Boolean.TRUE.equals(decorated.sampled())) {
      return RealSpan.create(decorated, recorder, errorHandler);
    }
    return NoopSpan.create(decorated);
  }