public Span toSpan(TraceContext context) {
    if (context == null) throw new NullPointerException("context == null");
    if (noop.get() == false && Boolean.TRUE.equals(context.sampled())) {
      return RealSpan.create(context, clock, recorder);
    }
    return NoopSpan.create(context);
  }