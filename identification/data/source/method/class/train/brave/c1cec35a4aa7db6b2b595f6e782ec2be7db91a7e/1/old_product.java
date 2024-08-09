public Span toSpan(TraceContext context) {
    if (context == null) throw new NullPointerException("context == null");
    if (!noop.get() && Boolean.TRUE.equals(context.sampled())) {
      return RealSpan.create(context, recorder);
    }
    return NoopSpan.create(context);
  }