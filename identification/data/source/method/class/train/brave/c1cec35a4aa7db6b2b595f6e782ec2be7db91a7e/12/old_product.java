public Span toSpan(TraceContext context) {
    if (context == null) throw new NullPointerException("context == null");
    if (context.sampled()) {
      return new RealSpan(context, clock, recorder);
    }
    return new NoopSpan(context);
  }