public Span toSpan(TraceContext context) {
    if (context == null) throw new NullPointerException("context == null");
    if (isDecorated(context)) return _toSpan(context);

    return _toSpan(decorateContext(
      InternalPropagation.instance.flags(context),
      context.traceIdHigh(),
      context.traceId(),
      context.localRootId(),
      context.parentIdAsLong(),
      context.spanId(),
      context.extra()
    ));
  }